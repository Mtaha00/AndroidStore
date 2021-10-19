package com.example.androidstore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.URL.Api;
import com.example.androidstore.URL.RetrofitInstance;
import com.example.androidstore.activity.DetailCategoryActivity;
import com.example.androidstore.adapter.CategoryAdapter.AllCategoryAdapter;
import com.example.androidstore.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment implements AllCategoryAdapter.SetOnItemClickListener {

    RecyclerView recyclerView;
    AllCategoryAdapter allCategoryAdapter;
    List<Category> categoryList = new ArrayList<>();




    private static final String TAG = "CategoryFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);


        setCategory(view);

        return view;
    }

    private void setCategory(View view) {

        recyclerView = view.findViewById(R.id.recycler_category_category);

        allCategoryAdapter = new AllCategoryAdapter(categoryList, getContext());

        recyclerView.setAdapter(allCategoryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);


        Api api = RetrofitInstance.getInstance();
        api.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = response.body();
                for (Category category : categories) {
                    categoryList.add(category);
                    allCategoryAdapter.notifyDataSetChanged();
                }
                Log.i(TAG, "onResponse: all category");
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.i(TAG, "onFailure: all category " + String.valueOf(t));
            }
        });

        allCategoryAdapter.onItemClickListener(this);

    }

    @Override
    public void onItemClick(int position, Category category) {
        Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
        intent.putExtra(Key.id, category.getId());
        intent.putExtra(Key.title, category.getTitle());
        startActivity(intent);

    }


}