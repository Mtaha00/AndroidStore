package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.ShowListProductAdapter.DetailCategoryProductAdapter;
import com.example.androidstore.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCategoryDetailProduct extends AppCompatActivity {
    private static final String TAG = "ShowCategoryDetailProdu";

    Bundle bundle;
    String titleTxt;
    String id;
    TextView title;

    //detail product
    List<Product> productList=new ArrayList<>();
    RecyclerView productRecycler;
    DetailCategoryProductAdapter detailCategoryProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category_detail_product);

        init();
        setProduct();

    }


    private void init() {
        title=findViewById(R.id.name_detail_category_product);

        bundle= getIntent().getExtras();
        titleTxt= bundle.getString(Key.title);
        id=bundle.getString(Key.id);

        title.setText(titleTxt);

    }

    private void setProduct() {
        productRecycler=findViewById(R.id.recycler_show_category_detail_product);
        detailCategoryProductAdapter=new DetailCategoryProductAdapter(this,productList);
        productRecycler.setHasFixedSize(true);
        productRecycler.setLayoutManager(new LinearLayoutManager(this));
        productRecycler.setAdapter(detailCategoryProductAdapter);

        Map<String,String> map=new HashMap<>();
        map.put(Key.id,id);

        Api api= RetrofitInstance.getInstance();
        api.getCategoryDetailProduct(map).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products=response.body();
                for (Product product:products){
                    productList.add(product);
                    detailCategoryProductAdapter.notifyDataSetChanged();
                }
                Log.i(TAG, "onResponse: ShowCategoryDetailProduct OK 123");

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i(TAG, "onFailure: ShowCategoryDetailProduct "+String.valueOf(t) );

            }
        });


    }
}