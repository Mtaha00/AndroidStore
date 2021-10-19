package com.example.androidstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.adapter.ProductAdapter.CompareSimilarAdapter;
import com.example.androidstore.model.Product;
import com.example.androidstore.model.SimilarModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompareActivity extends AppCompatActivity implements CompareSimilarAdapter.SetOnClickListener {

    private static final String TAG = "CompareActivity";

    Bundle bundle;
    String detail_category_id;
    String id_first;

    List<SimilarModel> similarList = new ArrayList<>();
    CompareSimilarAdapter compareSimilarAdapter;
    RecyclerView compareRecycler;

    String sImage1;
    String sPrice1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        bundle = getIntent().getExtras();
        id_first=bundle.getString(Key.id);
        detail_category_id = bundle.getString(Key.detail_category_id);


        compareRecycler = findViewById(R.id.recycler_compare_product);
        compareSimilarAdapter = new CompareSimilarAdapter(this, similarList);
        compareRecycler.setHasFixedSize(true);
        compareRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        compareRecycler.setAdapter(compareSimilarAdapter);

        Map<String, String> map = new HashMap<>();
        map.put(Key.id, detail_category_id);

        Api api = RetrofitInstance.getInstance();
        api.getCompareSimilar(map).enqueue(new Callback<List<SimilarModel>>() {
            @Override
            public void onResponse(Call<List<SimilarModel>> call, Response<List<SimilarModel>> response) {

                List<SimilarModel> similars = response.body();
                for (SimilarModel similarModel : similars) {
                    similarList.add(similarModel);

                    compareSimilarAdapter.notifyDataSetChanged();
                }


                Log.i(TAG, "onResponse: compare list ok");
            }

            @Override
            public void onFailure(Call<List<SimilarModel>> call, Throwable t) {
                Log.i(TAG, "onFailure: compare list" + t.toString());
            }
        });




        compareSimilarAdapter.onItemClickListener(this);



        sImage1=bundle.getString(Key.img1);
        sPrice1=bundle.getString(Key.price1);
    }


    @Override
    public void onItemClick(int position, SimilarModel similar) {
        Intent intent = new Intent(CompareActivity.this, CompareProductActivity.class);
        intent.putExtra(Key.id_second, similarList.get(position).getId());
        intent.putExtra(Key.id_first, id_first);

        startActivity(intent);
    }
}