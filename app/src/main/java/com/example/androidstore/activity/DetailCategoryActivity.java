package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.DetailCategoryAdapter.DetailCategoryAdapter;
import com.example.androidstore.adapter.DetailCategoryAdapter.PopularAdapter;
import com.example.androidstore.adapter.DetailCategoryAdapter.PopularDetailAdapter;
import com.example.androidstore.model.DetailCategory;
import com.example.androidstore.model.PopularModel;
import com.example.androidstore.model.popular.PopularA;
import com.example.androidstore.model.popular.PopularAmazing;
import com.example.androidstore.model.popular.PopularFirst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCategoryActivity extends AppCompatActivity implements DetailCategoryAdapter.SetOnItemClick {

    Bundle bundle;
    String titleTxt;
    String id;
    TextView title;
    ImageView backBtn;

    //popular first
    RecyclerView firstPopularRecycler;
    List<PopularA> popularAList = new ArrayList<>();
    PopularAdapter popularAdapter;

    //Popular Product
    List<PopularModel> popularModelList=new ArrayList<>();
    PopularDetailAdapter popularDetailAdapter;
    RecyclerView popularRecycler;


    private static final String TAG = "DetailCategoryActivity";

    //category
    List<DetailCategory> detailCategoryList=new ArrayList<>();
    DetailCategoryAdapter detailCategoryAdapter;
    RecyclerView recDetailCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        init();
        bundle=getIntent().getExtras();
        setToolbar();
        setAllDetailCategory();
        setPopularFirst();
        getPopular();


    }

    public void init(){
        title=findViewById(R.id.name_detail_category);
        backBtn=findViewById(R.id.back_detail_category);
    }
    public void setToolbar(){
        titleTxt=bundle.getString(Key.title);
        id=bundle.getString(Key.id);
        title.setText(titleTxt);
    }
    public void setAllDetailCategory(){
        recDetailCategory=findViewById(R.id.recycler_detail_category);
        detailCategoryAdapter=new DetailCategoryAdapter(detailCategoryList,this);
        recDetailCategory.setHasFixedSize(true);
        recDetailCategory.setLayoutManager(new GridLayoutManager(this,2));
        recDetailCategory.setAdapter(detailCategoryAdapter);

        Api api= RetrofitInstance.getInstance();

        Map<String,String> map=new HashMap<>();
        map.put(Key.id,id);
        api.setDetailCategory(map).enqueue(new Callback<List<DetailCategory>>() {
            @Override
            public void onResponse(Call<List<DetailCategory>> call, Response<List<DetailCategory>> response) {
                List<DetailCategory> detailCategories=response.body();
                for (DetailCategory detailCategory:detailCategories){
                    detailCategoryList.add(detailCategory);
                    detailCategoryAdapter.notifyDataSetChanged();

                }
                Log.i(TAG, "onResponse: all category");
            }

            @Override
            public void onFailure(Call<List<DetailCategory>> call, Throwable t) {

            }
        });
    }
    private void setPopularFirst() {
        firstPopularRecycler =findViewById(R.id.recycler_popular_category);
        firstPopularRecycler.setHasFixedSize(true);
        firstPopularRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        PopularFirst popularFirst = new PopularFirst("پیشنهادات برای شما", "https://pngkit.com/png/full/28-283565_discount-tag-png.png");
        popularAList.add(new PopularA(1, popularFirst));
        popularAdapter = new PopularAdapter(this, popularAList);
        firstPopularRecycler.setAdapter(popularAdapter);

        Api api = RetrofitInstance.getInstance();

        Map<String,String> map=new HashMap<>();
        map.put(Key.id,id);

        api.getPopularAmazing(map).enqueue(new Callback<List<PopularAmazing>>() {
            @Override
            public void onResponse(Call<List<PopularAmazing>> call, Response<List<PopularAmazing>> response) {
                List<PopularAmazing> popularAmazings = response.body();
                for (PopularAmazing popularAmazing : popularAmazings) {
                    popularAList.add(new PopularA(0, popularAmazing));

                    popularAdapter.notifyDataSetChanged();
                }

                Log.i(TAG, "success: PopularFirst OK");
            }

            @Override
            public void onFailure(Call<List<PopularAmazing>> call, Throwable t) {
                Log.i(TAG, "onFailure: PopularFirst " + t.toString());

            }

        });
        detailCategoryAdapter.setOnItemClickListener(this);


    }


    @Override
    public void onClick(int position, DetailCategory detailCategory) {
        Intent intent=new Intent(DetailCategoryActivity.this,ShowCategoryDetailProduct.class);
        intent.putExtra(Key.title,detailCategory.getName());
        intent.putExtra(Key.id,detailCategory.getId());
        startActivity(intent);
    }
    public void getPopular(){
        popularRecycler=findViewById(R.id.recycler_popular_detailCategory);
        popularDetailAdapter=new PopularDetailAdapter(this,popularModelList);
        popularRecycler.setHasFixedSize(true);
        popularRecycler.setLayoutManager(new LinearLayoutManager(this));
        popularRecycler.setAdapter(popularDetailAdapter);

        Api api=RetrofitInstance.getInstance();

        Map<String,String> map=new HashMap<>();
        map.put(Key.id,id);

        api.getPopularDetailCategory(map).enqueue(new Callback<List<PopularModel>>() {
            @Override
            public void onResponse(Call<List<PopularModel>> call, Response<List<PopularModel>> response) {
                List<PopularModel> popularModels=response.body();
                for (PopularModel popularModel:popularModels){
                    popularModelList.add(popularModel);
                    popularDetailAdapter.notifyDataSetChanged();

                }
                Log.i(TAG, "onResponse: popular ok");
            }

            @Override
            public void onFailure(Call<List<PopularModel>> call, Throwable t) {
                Log.i(TAG, "onFailure: popular" +t.toString());
            }
        });



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }
}