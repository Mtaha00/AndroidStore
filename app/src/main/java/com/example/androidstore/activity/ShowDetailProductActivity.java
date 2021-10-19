package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.ProductAdapter.ImageProductAdapter;
import com.example.androidstore.adapter.ProductAdapter.PropertyProductAdapter;
import com.example.androidstore.adapter.ProductAdapter.SimilarProductAdapter;
import com.example.androidstore.model.PopularModel;
import com.example.androidstore.model.SimilarModel;
import com.example.androidstore.model.detailProduct.ImageProduct;
import com.example.androidstore.model.detailProduct.PropertyProduct;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailProductActivity extends AppCompatActivity {
    private static final String TAG = "ShowDetailProductActivi";

    TextView nameProductTxt;
    TextView nameBrandTxt;
    String id;
    String nameBrandS;
    String nameProductS;
    String category_id;
    String detail_category_id;
    Bundle bundle;

    //    Toolbar
    ImageView moreDetailImg;
    ImageView favoriteImg;
    ImageView shoppingImg;

    //    Image Product
    List<ImageProduct> imageProductList = new ArrayList<>();
    ImageProductAdapter imageProductAdapter;
    ViewPager viewPagerImg;
    TabLayout tabLayout;

    //    similar product
    List<SimilarModel> similarList = new ArrayList<>();
    SimilarProductAdapter similarProductAdapter;
    RecyclerView similarRecycler;

    //    Property product
    List<PropertyProduct> propertyList = new ArrayList<>();
    PropertyProductAdapter propertyAdapter;
    RecyclerView propertyRecycler;

    //    buy bottom
    Button buyBtn;
    ElegantNumberButton elegantNumberButton;
    TextView priceTxt;
    TextView priceOffTxt;
    TextView valueOffTxt;

    //Specification
    RelativeLayout specificationView;

    //review
    RelativeLayout reviweView;

    String price;
    String priceOff;
    String valueOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_product);

        init();
        setToolbar();
        setImageSlider();
        setSimilarProduct();
        setProperty();
        setBuyLayout();
        setSpecification();
        setReviewProduct();

    }


    private void init() {
        bundle = getIntent().getExtras();
        id = bundle.getString(Key.id);
        nameBrandS = bundle.getString(Key.brand);
        nameProductS = bundle.getString(Key.title);
        category_id = bundle.getString(Key.category_id);

        nameProductTxt = findViewById(R.id.name_detail_product);
        nameBrandTxt = findViewById(R.id.brand_detail_product);

        nameProductTxt.setText("نام مجصول: " + nameProductS);
        nameBrandTxt.setText("برند: " + nameBrandS);

        valueOffTxt = findViewById(R.id.valueOff_buy_detail_product);
        priceTxt = findViewById(R.id.txt_price_buy_detail_product);
        priceOffTxt = findViewById(R.id.txt_priceOff_buy_detail_product);
        buyBtn = findViewById(R.id.btn_buy_detail_product);
        elegantNumberButton = (ElegantNumberButton) findViewById(R.id.elegant_buy_detail_product);


    }


    private void setToolbar() {
        moreDetailImg = findViewById(R.id.more_detail_product);
        favoriteImg = findViewById(R.id.favorite_detail_product);
        shoppingImg = findViewById(R.id.shopping_detail_product);

        moreDetailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ShowDetailProductActivity.this);
                View bottomSheetLayout = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.layout_bottom_sheet_product, findViewById(R.id.bottom_sheet_child));

                LinearLayout compare = bottomSheetLayout.findViewById(R.id.compare_bottomSheet_product);
                LinearLayout chart = bottomSheetLayout.findViewById(R.id.chart_bottomSheet_product);
                LinearLayout share = bottomSheetLayout.findViewById(R.id.share_bottomSheet_product);

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "اپلیکیشن فروشگاهی");
                        String massage = "اسم محصول: " + nameProductS;
                        intent.putExtra(Intent.EXTRA_TEXT, massage);
                        startActivity(Intent.createChooser(intent, "اپلیکیشن فروشگاهی"));
                    }
                });

                chart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShowDetailProductActivity.this, ChartActivity.class);
                        intent.putExtra(Key.id, id);
                        startActivity(intent);
                    }
                });

                compare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ShowDetailProductActivity.this, CompareActivity.class);
                        intent.putExtra(Key.id, id);
                        intent.putExtra(Key.detail_category_id, detail_category_id);

                        intent.putExtra(Key.id_first,id);

                        startActivity(intent);
                    }
                });


                bottomSheetDialog.setContentView(bottomSheetLayout);
                bottomSheetDialog.show();
                }
            });

        }


        private void setImageSlider () {
            viewPagerImg = findViewById(R.id.viewpager_detail_product);
            tabLayout = findViewById(R.id.tabLayout_detail_product);

            imageProductAdapter = new ImageProductAdapter(imageProductList, this);

            viewPagerImg.setRotationY(180);

            viewPagerImg.setAdapter(imageProductAdapter);
            tabLayout.setupWithViewPager(viewPagerImg, true);

            Map<String, String> map = new HashMap<>();
            map.put(Key.id, id);

            Api api = RetrofitInstance.getInstance();
            api.getImageProduct(map).enqueue(new Callback<List<ImageProduct>>() {
                @Override
                public void onResponse(Call<List<ImageProduct>> call, Response<List<ImageProduct>> response) {
                    List<ImageProduct> imageProducts = response.body();
                    for (ImageProduct imageProduct : imageProducts) {
                        imageProductList.add(imageProduct);
                        imageProductAdapter.notifyDataSetChanged();
                    }
                    Log.i(TAG, "onResponse: Show Detail product : Ok");
                }

                @Override
                public void onFailure(Call<List<ImageProduct>> call, Throwable t) {
                    Log.i(TAG, "onFailure: Show Detail product : " + t.toString());
                }
            });
        }

        private void setSimilarProduct () {
            detail_category_id = bundle.getString(Key.detail_category_id);

            similarRecycler = findViewById(R.id.recycler_similar_detail_product);
            similarProductAdapter = new SimilarProductAdapter(this, similarList);
            similarRecycler.setHasFixedSize(true);
            similarRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            similarRecycler.setAdapter(similarProductAdapter);

            Map<String, String> map = new HashMap<>();

//        کتگوری ای دی رو هنوز باندل نزدی
//        باید سمت سرور هم از کتگوری ایدی استفاده کتی ن ای دی 13:36 35
            map.put(Key.id, detail_category_id);
            Api api = RetrofitInstance.getInstance();
            api.getSimilarProduct(map).enqueue(new Callback<List<SimilarModel>>() {
                @Override
                public void onResponse(Call<List<SimilarModel>> call, Response<List<SimilarModel>> response) {
                    List<SimilarModel> similarModels = response.body();

                    for (SimilarModel similarModel : similarModels) {
                        if (!(similarModel.getId().equals(id))) {

                            similarList.add(similarModel);


                            similarProductAdapter.notifyDataSetChanged();

                        }
                    }

                    Log.i(TAG, "onResponse: SimilarModel OK");
                }

                @Override
                public void onFailure(Call<List<SimilarModel>> call, Throwable t) {
                    Log.i(TAG, "onFailure: SimilarModel " + t.toString());
                }
            });


        }

        private void setProperty () {
            propertyRecycler = findViewById(R.id.recycler_property_detail_product);
            propertyAdapter = new PropertyProductAdapter(this, propertyList);
            propertyRecycler.setHasFixedSize(true);
            propertyRecycler.setLayoutManager(new LinearLayoutManager(this));
            propertyRecycler.setAdapter(propertyAdapter);

            Map<String, String> map = new HashMap<>();

            map.put(Key.id, id);

            Api api = RetrofitInstance.getInstance();
            api.getPropertyProduct(map).enqueue(new Callback<List<PropertyProduct>>() {
                @Override
                public void onResponse(Call<List<PropertyProduct>> call, Response<List<PropertyProduct>> response) {

                    List<PropertyProduct> propertys = response.body();
                    for (PropertyProduct property : propertys) {
                        propertyList.add(property);
                        propertyAdapter.notifyDataSetChanged();

                    }
                    propertyAdapter.notifyDataSetChanged();


                    Log.i(TAG, "onResponse: PropertyProduct OK");
                }

                @Override
                public void onFailure(Call<List<PropertyProduct>> call, Throwable t) {
                    Log.i(TAG, "onFailure: PropertyProduct +" + String.valueOf(t));
                }
            });


        }


        private void setSpecification () {
            specificationView = findViewById(R.id.relative1_specification);
            specificationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShowDetailProductActivity.this, SpecificationsProductActivity.class);
                    intent.putExtra(Key.id, id);
                    startActivity(intent);
                }
            });

        }


        private void setReviewProduct () {
            reviweView = findViewById(R.id.relative2_review);

            reviweView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShowDetailProductActivity.this, ReviewProduct.class);
                    intent.putExtra(Key.id, id);
                    startActivity(intent);
                }
            });


        }


        private void setBuyLayout () {
            price = bundle.getString(Key.price);
            priceOff = bundle.getString(Key.priceOff);
            valueOff = bundle.getString(Key.valueOffer);


            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            String txt_price_dec = decimalFormat.format(Integer.valueOf(price));
            String txt_off_price_dec = decimalFormat.format(Integer.valueOf(priceOff));

            if (price.equals(priceOff)) {
                priceTxt.setVisibility(View.INVISIBLE);
                priceOffTxt.setText(txt_price_dec + " تومان ");
                valueOffTxt.setVisibility(View.GONE);

            } else {
                priceOffTxt.setText(txt_off_price_dec + " تومان ");
                valueOffTxt.setText(valueOff + "%");

                SpannableString spannableString = new SpannableString(txt_price_dec);
                spannableString.setSpan(new StrikethroughSpan(), 0, price.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                priceTxt.setText(spannableString);
            }

            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    elegantNumberButton.setVisibility(View.VISIBLE);
                    elegantNumberButton.setNumber("1");
                    buyBtn.setVisibility(View.GONE);
                }
            });

            elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String eleg = elegantNumberButton.getNumber();
                    if (eleg.equals("0")) {
                        buyBtn.setVisibility(View.VISIBLE);
                        elegantNumberButton.setVisibility(View.GONE);
                    }
                }
            });


        }

    }