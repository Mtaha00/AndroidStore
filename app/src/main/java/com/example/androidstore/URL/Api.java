package com.example.androidstore.URL;

import com.example.androidstore.model.Amazing.AmazingOffer;
import com.example.androidstore.model.Banner;
import com.example.androidstore.model.Brand;
import com.example.androidstore.model.Category;
import com.example.androidstore.model.DetailCategory;
import com.example.androidstore.model.PopularModel;
import com.example.androidstore.model.Product;
import com.example.androidstore.model.SimilarModel;
import com.example.androidstore.model.SpecialProduct.SpecialProduct;
import com.example.androidstore.model.detailProduct.ChartProduct;
import com.example.androidstore.model.detailProduct.CompareModel;
import com.example.androidstore.model.detailProduct.ImageProduct;
import com.example.androidstore.model.detailProduct.ProductFCompare;
import com.example.androidstore.model.detailProduct.PropertyProduct;
import com.example.androidstore.model.detailProduct.ReviewModel;
import com.example.androidstore.model.detailProduct.SpecificationModel;
import com.example.androidstore.model.popular.PopularAmazing;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @GET("getBanner.php")
    Call<List<Banner>> getBanner();

    @GET("getCategory.php")
    Call<List<Category>> getCategory();

    @GET("getOfferProduct.php")
    Call<List<AmazingOffer>> getOfferProduct();

    @GET("getBannerSecond.php")
    Call<List<Banner>> getSecondBanner();

    @GET("getSpecialProduct.php")
    Call<List<SpecialProduct>> getSpecialProduct();

    @GET("getNewProduct.php")
    Call<List<Product>> getNewProduct();

    @GET("getBrand.php")
    Call<List<Brand>> getBrand();

    @GET("getAllCategory.php")
    Call<List<Category>> getAllCategory();

    @FormUrlEncoded
    @POST("getPopularAmazing.php")
    Call<List<PopularAmazing>> getPopularAmazing(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("getDetailCategory.php")
    Call<List<DetailCategory>> setDetailCategory(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("getPopularDetailCategory.php")
    Call<List<PopularModel>> getPopularDetailCategory(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("getCategoryDetailProduct.php")
    Call<List<Product>> getCategoryDetailProduct(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("getImageProduct.php")
    Call<List<ImageProduct>> getImageProduct(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getSimilarProduct.php")
    Call<List<SimilarModel>> getSimilarProduct(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getPropertyProduct.php")
    Call<List<PropertyProduct>> getPropertyProduct(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getChart.php")
    Call<List<ChartProduct>> getChart(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getSpecification.php")
    Call<List<SpecificationModel>> getSpecification(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getReviewProduct.php")
    Call<List<ReviewModel>> getReviewProduct(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getCompareSimilar.php")
    Call<List<SimilarModel>> getCompareSimilar(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getFirstCompare.php")
    Call<List<CompareModel>> getFirstCompare(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getSecondCompare.php")
    Call<List<CompareModel>> getSecondCompare(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("getProductCompare.php")
    Call<List<ProductFCompare>> getProductCompare(@FieldMap Map<String,String> map);

}
