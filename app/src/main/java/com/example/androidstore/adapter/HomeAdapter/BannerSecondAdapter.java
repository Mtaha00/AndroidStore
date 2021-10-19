package com.example.androidstore.adapter.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.model.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerSecondAdapter extends RecyclerView.Adapter<BannerSecondAdapter.Holder>{

    Context context;
    List<Banner> banners;

    public BannerSecondAdapter(Context context, List<Banner> banners) {
        this.context = context;
        this.banners = banners;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_banner_second,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Picasso.get().load(banners.get(position).getLink_img()).into(holder.imgBanner);

    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imgBanner;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imgBanner=itemView.findViewById(R.id.img_banner_second);
        }
    }
}
