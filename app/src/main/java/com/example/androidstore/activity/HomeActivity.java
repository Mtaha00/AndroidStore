package com.example.androidstore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidstore.R;
import com.example.androidstore.fragment.CategoryFragment;
import com.example.androidstore.fragment.HomeFragment;
import com.example.androidstore.fragment.ProfileFragment;
import com.example.androidstore.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_home_container, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nave_home:
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_home_container, homeFragment);
                fragmentTransaction.commit();
                break;
            case R.id.nave_search:
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame_home_container, searchFragment);
                fragmentTransaction1.commit();
                break;
            case R.id.nave_category:
                CategoryFragment categoryFragment = new CategoryFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.frame_home_container, categoryFragment);
                fragmentTransaction2.commit();
                break;
            case R.id.nave_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_home_container, new ProfileFragment()).commit();
                break;

        }

        return true;
    }
}
