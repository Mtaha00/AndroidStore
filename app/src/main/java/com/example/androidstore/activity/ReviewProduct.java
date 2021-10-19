package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.ProductAdapter.ReviewAdapter;
import com.example.androidstore.model.detailProduct.ReviewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewProduct extends AppCompatActivity {

    Bundle bundle;
    String id;
    RecyclerView reviewRecycler;
    ReviewAdapter reviewAdapter;
    List<ReviewModel> reviewList=new ArrayList<>();

    private static final String TAG = "ReviewProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_product);

        bundle=getIntent().getExtras();
        id=bundle.getString(Key.id);


        reviewRecycler=findViewById(R.id.recycler_review);
        reviewAdapter=new ReviewAdapter(this,reviewList);
        reviewRecycler.setAdapter(reviewAdapter);
        reviewRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        reviewRecycler.setHasFixedSize(true);

        Map<String ,String> map=new HashMap<>();
        map.put(Key.id,id);

        Api api= RetrofitInstance.getInstance();
        api.getReviewProduct(map).enqueue(new Callback<List<ReviewModel>>() {
            @Override
            public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {

                List<ReviewModel> reviews =response.body();

                for (ReviewModel reviewModel:reviews){

                    reviewList.add(reviewModel);

                    reviewAdapter.notifyDataSetChanged();
                }

                Log.i(TAG, "onResponse: review Ok");
            }

            @Override
            public void onFailure(Call<List<ReviewModel>> call, Throwable t) {
                Log.i(TAG, "onFailure: review product" +t.toString());
            }
        });

    }
}