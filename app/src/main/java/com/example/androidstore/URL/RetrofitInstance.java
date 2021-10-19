package com.example.androidstore.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static final String BASE_URL="http://192.168.1.51/shopping/";
//    public static final String BASE_URL="http://192.168.136.26/shopping/";

    private static Retrofit retrofit=null;

    public static Api getInstance(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(Api.class) ;
    }


}
