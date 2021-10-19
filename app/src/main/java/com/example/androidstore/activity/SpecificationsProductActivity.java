package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.ProductAdapter.SpecificationProductAdapter;
import com.example.androidstore.model.SpecialProduct.SpecialProduct;
import com.example.androidstore.model.detailProduct.SpecificationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecificationsProductActivity extends AppCompatActivity {
    private static final String TAG = "SpecificationsProductAc";

    Bundle bundle;
    String id;
    List<SpecificationModel> specificationList=new ArrayList<>();
    RecyclerView specificationRecycler;
    SpecificationProductAdapter specificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications_product);

        bundle=getIntent().getExtras();
        id=bundle.getString(Key.id);


        specificationRecycler=findViewById(R.id.recycler_specification_product);
        specificationAdapter=new SpecificationProductAdapter(this,specificationList);
        specificationRecycler.setHasFixedSize(true);
        specificationRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        specificationRecycler.setAdapter(specificationAdapter);

        Map<String,String> map=new HashMap<>();
        map.put(Key.id,id);

        Api api= RetrofitInstance.getInstance();
        api.getSpecification(map).enqueue(new Callback<List<SpecificationModel>>() {
            @Override
            public void onResponse(Call<List<SpecificationModel>> call, Response<List<SpecificationModel>> response) {
                List<SpecificationModel> specifications=response.body();

                for (SpecificationModel specificationModel:specifications){
                    specificationList.add(specificationModel);
                    specificationAdapter.notifyDataSetChanged();
                }

                Log.i(TAG, "onResponse: Specification OK");
            }

            @Override
            public void onFailure(Call<List<SpecificationModel>> call, Throwable t) {
                Log.i(TAG, "onFailure: Specification"+t.toString());
            }
        });


    }
}