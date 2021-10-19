package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.ProductAdapter.CompareProductAdapter;
import com.example.androidstore.model.SimilarModel;
import com.example.androidstore.model.detailProduct.CompareModel;
import com.example.androidstore.model.detailProduct.ProductFCompare;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareProductActivity extends AppCompatActivity {

    RecyclerView compareRecycler;
    CompareProductAdapter compareAdapter;
    List<CompareModel> compareFirstList =new ArrayList<>();
    List<CompareModel> compareSecondList=new ArrayList<>();

    Bundle bundle;
    String firstId;
    String secondId;


    ImageView imageView1;
    ImageView imageView2;

    TextView textPrice1;
    TextView textPrice2;

    TextView textName1;
    TextView textName2;

    String price1;
    String price2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_product);


        bundle=getIntent().getExtras();
        firstId=bundle.getString(Key.id_first);
        secondId=bundle.getString(Key.id_second);




        compareRecycler=findViewById(R.id.recycler_compare_two_product);
        compareAdapter=new CompareProductAdapter(this, compareFirstList,compareSecondList);
        compareRecycler.setAdapter(compareAdapter);
        compareRecycler.setHasFixedSize(true);
        compareRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        Api api= RetrofitInstance.getInstance();

        Map<String ,String> map1=new HashMap<>();
        map1.put(Key.id,firstId);

        api.getFirstCompare(map1).enqueue(new Callback<List<CompareModel>>() {
            @Override
            public void onResponse(Call<List<CompareModel>> call, Response<List<CompareModel>> response) {
                List<CompareModel> compareModels1 =response.body();
                for (CompareModel compareModel : compareModels1){
                    compareFirstList.add(compareModel);
                    compareAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<CompareModel>> call, Throwable t) {

            }
        });

        Api api2= RetrofitInstance.getInstance();

        Map<String ,String> map2=new HashMap<>();
        map2.put(Key.id,secondId);

        api2.getSecondCompare(map2).enqueue(new Callback<List<CompareModel>>() {
            @Override
            public void onResponse(Call<List<CompareModel>> call, Response<List<CompareModel>> response) {
                List<CompareModel> compareSeconds2=response.body();
                for (CompareModel compareModel :compareSeconds2){
                    compareSecondList.add(compareModel);
                    compareAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<CompareModel>> call, Throwable t) {

            }
        });




        init();

    }

    private void init(){


        imageView1=findViewById(R.id.firstImg_compare_product);
        imageView2=findViewById(R.id.SecondImg_compare_product);
        textName1=findViewById(R.id.firstTxt_compare_product);
        textName2=findViewById(R.id.secondTxt_compare_product);
        textPrice1=findViewById(R.id.firstPrice_compare_product);
        textPrice2=findViewById(R.id.secondPrice_compare_product);


        Api api3=RetrofitInstance.getInstance();
        Map<String ,String> map3=new HashMap<>();


        DecimalFormat decimalFormat=new DecimalFormat("###,###");


        map3.put(Key.id,firstId);
        api3.getProductCompare(map3).enqueue(new Callback<List<ProductFCompare>>() {
            @Override
            public void onResponse(Call<List<ProductFCompare>> call, Response<List<ProductFCompare>> response) {
                List<ProductFCompare> productFCompares=response.body();

                for (ProductFCompare fCompare:productFCompares){
                    Picasso.get().load(fCompare.getLink_img()).into(imageView1);
                    textName1.setText(fCompare.getName());

                    price1=decimalFormat.format(Integer.valueOf(fCompare.getPrice()));
                    textPrice1.setText(price1+" تومان ");

                }

            }

            @Override
            public void onFailure(Call<List<ProductFCompare>> call, Throwable t) {

            }
        });

        Api api4=RetrofitInstance.getInstance();
        Map<String ,String> map4=new HashMap<>();

        map4.put(Key.id,secondId);
        api4.getProductCompare(map4).enqueue(new Callback<List<ProductFCompare>>() {
            @Override
            public void onResponse(Call<List<ProductFCompare>> call, Response<List<ProductFCompare>> response) {
                List<ProductFCompare> productFCompares=response.body();

                for (ProductFCompare fCompare:productFCompares){
                    Picasso.get().load(fCompare.getLink_img()).into(imageView2);
                    textName2.setText(fCompare.getName());

                    price2=decimalFormat.format(Integer.valueOf(fCompare.getPrice()));
                    textPrice2.setText(price2+" تومان ");
                }

            }

            @Override
            public void onFailure(Call<List<ProductFCompare>> call, Throwable t) {

            }
        });




    }

}