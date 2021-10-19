package com.example.androidstore.adapter.ProductAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidstore.model.detailProduct.ImageProduct;
import com.example.androidstore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageProductAdapter extends PagerAdapter {

    List<ImageProduct> data;
    Context context;

    public ImageProductAdapter(List<ImageProduct> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_image_product,container,false);
        ImageView imgProduct=view.findViewById(R.id.image_item_product);
        imgProduct.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.get().load(data.get(position).getLink_img()).into(imgProduct);

        view.setRotationY(-180);

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull  View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
