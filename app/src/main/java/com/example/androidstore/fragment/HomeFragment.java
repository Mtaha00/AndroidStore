package com.example.androidstore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.activity.DetailCategoryActivity;
import com.example.androidstore.adapter.HomeAdapter.AmazingProductAdapter;
import com.example.androidstore.adapter.HomeAdapter.BannerSecondAdapter;
import com.example.androidstore.adapter.HomeAdapter.BrandAdapter;
import com.example.androidstore.adapter.HomeAdapter.CategoryAdapter;
import com.example.androidstore.adapter.HomeAdapter.NewProductAdapter;
import com.example.androidstore.adapter.HomeAdapter.SliderAdapter;
import com.example.androidstore.adapter.HomeAdapter.SpecialProductAdapter;
import com.example.androidstore.model.Amazing.Amazing;
import com.example.androidstore.model.Amazing.AmazingFirst;
import com.example.androidstore.model.Amazing.AmazingOffer;
import com.example.androidstore.model.Banner;
import com.example.androidstore.model.Brand;
import com.example.androidstore.model.Category;
import com.example.androidstore.model.Product;
import com.example.androidstore.model.SpecialProduct.Special;
import com.example.androidstore.model.SpecialProduct.SpecialProduct;
import com.example.androidstore.model.SpecialProduct.SpecialProductFirst;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements CategoryAdapter.SetOnItemClickListener {
    View view;
    //Banner
    List<Banner> listBanner = new ArrayList<>();
    SliderAdapter sliderAdapter;
    ViewPager viewPager;
    TabLayout tabs;
    RequestQueue requestQueue;

    //category
    List<Category> categoryList = new ArrayList<>();
    RecyclerView categoryRecycler;
    CategoryAdapter categoryAdapter;

    //AmazingOffer
    TextView offerTimer;
    List<Amazing> listAmazing=new ArrayList<>();
    AmazingProductAdapter amazingProductAdapter;
    RecyclerView amazingRecycler;

    //second banner
    List<Banner> listSecondBanner=new ArrayList<>();
    RecyclerView secondBannerRecycler;
    BannerSecondAdapter bannerSecondAdapter;

    //special product
    List<Special> specialList=new ArrayList<>();
    SpecialProductAdapter specialProductAdapter;
    RecyclerView specialRecycler;


    //new Product
    List<Product> newProductList=new ArrayList<>();
    RecyclerView newProductRecycler;
    NewProductAdapter newProductAdapter;
    
    //Brand
    List<Brand> brandList=new ArrayList<>();
    RecyclerView brandRecycler;
    BrandAdapter brandAdapter;



    Handler handler = new Handler();
    int currentPage = 0;

    private static final String TAG = "HomeFragment";

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        setSlider(view);
        setCategory(view);


        setAmazingOffer(view);
        setSecondBanner(view);
        setNewProduct(view);
        setBrandBanner(view);
        setSpecialProduct(view);
        
        return view;
    }


    public void setSlider(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        tabs = view.findViewById(R.id.tabs);
        sliderAdapter = new SliderAdapter(getContext(), listBanner);
        viewPager.setAdapter(sliderAdapter);
        tabs.setupWithViewPager(viewPager, true);

        viewPager.setRotationY(180);

        Api api = RetrofitInstance.getInstance();
        Call<List<Banner>> call = api.getBanner();
        call.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, retrofit2.Response<List<Banner>> response) {
                List<Banner> banners = response.body();
                for (Banner banner : banners) {
                    listBanner.add(banner);
                    sliderAdapter.notifyDataSetChanged();

                }
                Log.i(TAG, "onResponse: banner slider success");
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());
            }
        });

        sliderTimer();


        //        requestQueue= Volley.newRequestQueue(getContext());

//        String url= Link.LINK_BANNER_SLIDER;
//        Response.Listener<JSONArray> listener=new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                Gson gson=new Gson();
//                Banner[] banners=gson.fromJson(response.toString(),Banner[].class);
//                for (int i =0;i<banners.length;i++){
//                    listBanner.add(banners[i]);
//                    sliderAdapter.notifyDataSetChanged();
//                }
//            }
//        };
//        Response.ErrorListener errorListener=new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                Log.i(TAG, "onErrorResponse: "+error.getMessage());
//            }
//        };

//        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET,url,null,listener,errorListener);
//        requestQueue.add(request);

    }

    private void sliderTimer() {


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == listBanner.size()) {
                    currentPage = 0;
                    viewPager.setCurrentItem(currentPage);
                } else {
                    viewPager.setCurrentItem(currentPage);
                    currentPage++;

                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 400, 3000);
    }

    private void setCategory(View view) {

        categoryRecycler = view.findViewById(R.id.recyclerView_category_home);
        categoryRecycler.setHasFixedSize(true);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(categoryList, getContext());

        categoryRecycler.setAdapter(categoryAdapter);


        Api api = RetrofitInstance.getInstance();
        api.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = response.body();
                for (Category category : categories) {
                    categoryList.add(category);
                    categoryAdapter.notifyDataSetChanged();
                }
                Log.i(TAG, "onResponse: sucsess ");
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.i(TAG, "onFailure: category" + t.toString());
            }
        });

        categoryAdapter.onItemClickListener(this);

    }
    @Override
    public void onCategoryClickItem(int position, Category category) {
        Intent intent=new Intent(getContext(), DetailCategoryActivity.class);
        intent.putExtra(Key.id,category.getId());
        intent.putExtra(Key.title,category.getTitle());
        startActivity(intent);

    }

    private void setAmazingOffer(View view){
        setOfferTimer(view);

        amazingRecycler=view.findViewById(R.id.recycler_offer_home);
        amazingRecycler.setHasFixedSize(true);
        amazingRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        AmazingFirst amazingFirst=new AmazingFirst("پیشنهادات شگفت انگیز امروز","https://pngkit.com/png/full/28-283565_discount-tag-png.png");

        listAmazing.add(new Amazing(1,amazingFirst));
        amazingProductAdapter=new AmazingProductAdapter(getContext(),listAmazing);
        amazingRecycler.setAdapter(amazingProductAdapter);


        Api api=RetrofitInstance.getInstance();
        api.getOfferProduct().enqueue(new Callback<List<AmazingOffer>>() {
            @Override
            public void onResponse(Call<List<AmazingOffer>> call, Response<List<AmazingOffer>> response) {
                List<AmazingOffer> amazingOffers=response.body();
                for (AmazingOffer amazingOffer:amazingOffers){
                    listAmazing.add(new Amazing(0,amazingOffer));
                    amazingProductAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<AmazingOffer>> call, Throwable t) {
                Log.i(TAG, "onFailure: amazing offer "+t.toString() );
            }
        });
    }

    int second;
    int minute;
    int hour;
    String s;
    String m;
    String h;

    private void setOfferTimer(View view) {
        offerTimer = view.findViewById(R.id.timer_offer_home);
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        second = 60 - today.second;
        minute = 59 - today.minute;
        hour = 23 - today.hour;

        Handler tHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (second == 0) {
                    second = 59;
                    if (minute == 0) {
                        minute = 59;
                        if (hour == 0) {
                            hour = 23;
                        } else {
                            hour--;
                        }
                    } else {
                        minute--;
                    }
                } else {
                    second--;
                }

                if (second < 10) {
                    s = "0" + second;
                } else {
                    s = String.valueOf(second);
                }
                if (minute < 10) {
                    m = "0" + minute;
                } else {
                    m = String.valueOf(minute);
                }
                if (hour < 10) {
                    h = "0" + hour;
                } else {
                    h = String.valueOf(hour);
                }

                String time=h + ":" + m + ":" + s;
                offerTimer.setText(time);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tHandler.post(runnable);

            }
        }, 0, 1000);

    }

    private void setSecondBanner(View view){
        secondBannerRecycler=view.findViewById(R.id.recycler_second_banner_home);
        bannerSecondAdapter=new BannerSecondAdapter(getContext(),listSecondBanner);
        secondBannerRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        secondBannerRecycler.setAdapter(bannerSecondAdapter);

        Api api=RetrofitInstance.getInstance();
        api.getSecondBanner().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                List<Banner> banners=response.body();
                for (Banner banner:banners){
                    listSecondBanner.add(banner);
                }
                bannerSecondAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                Log.i(TAG, "onFailure: second banner"+String.valueOf(t));
            }
        });
    }
    private void setSpecialProduct(View view){
        specialRecycler=view.findViewById(R.id.recycler_specialProduct_home);
        specialRecycler.setHasFixedSize(true);
        specialRecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));


        SpecialProductFirst specialProductFirst=new SpecialProductFirst("پر فروش ترین ساعت ها","https://pngkit.com/png/full/28-283565_discount-tag-png.png");
        specialList.add(new Special(1,specialProductFirst));


        Api api=RetrofitInstance.getInstance();
        api.getSpecialProduct().enqueue(new Callback<List<SpecialProduct>>() {
            @Override
            public void onResponse(Call<List<SpecialProduct>> call, Response<List<SpecialProduct>> response) {
                List<SpecialProduct> specialProducts=response.body();
                for(SpecialProduct specialProduct:specialProducts){
                    specialList.add(new Special(0,specialProduct));
                    specialProductAdapter.notifyDataSetChanged();
                }
                Log.i(TAG, "success: Special OK" );
            }

            @Override
            public void onFailure(Call<List<SpecialProduct>> call, Throwable t) {
                Log.i(TAG, "onFailure: Special "+t.toString() );
            }
        });
        specialProductAdapter=new SpecialProductAdapter(getContext(),specialList);
        specialRecycler.setAdapter(specialProductAdapter);



    }





    private void setNewProduct(View view){
        newProductRecycler=view.findViewById(R.id.recycler_newProduct_home);
        newProductAdapter=new NewProductAdapter(getContext(),newProductList);
        newProductRecycler.setHasFixedSize(true);
        newProductRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        newProductRecycler.setAdapter(newProductAdapter);

        Api api=RetrofitInstance.getInstance();
        api.getNewProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products=response.body();
                for (Product product:products){
                    newProductList.add(product);
                    newProductAdapter.notifyDataSetChanged();
                }

                Log.i(TAG, "onResponse: new Product ok");
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i(TAG, "onFailure: new Product"+String.valueOf(t));
            }
        });

    }

    private void setBrandBanner(View view) {
        brandAdapter=new BrandAdapter(getContext(),brandList);
        brandRecycler=view.findViewById(R.id.brand_recycler_home);
        brandRecycler.setHasFixedSize(true);
        brandRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));        
        brandRecycler.setAdapter(brandAdapter);
        
        Api api=RetrofitInstance.getInstance();
        api.getBrand().enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                List<Brand> brands=response.body();
                for (Brand brand:brands){
                    brandList.add(brand);
                    brandAdapter.notifyDataSetChanged();

                }
                Log.i(TAG, "onResponse: brand Ok");


            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                Log.i(TAG, "onFailure: brand"+String.valueOf(t));
            }
        });
        
    }


}