package com.example.androidstore.adapter.HomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.model.Brand;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.Holder> {
    Context context;
    List<Brand> brands;

    public BrandAdapter(Context context, List<Brand> brands) {
        this.context = context;
        this.brands = brands;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.nameTxt.setText(brands.get(position).getName());
        Picasso.get().load(brands.get(position).getLing_img()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView nameTxt;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_brand);
            nameTxt = itemView.findViewById(R.id.name_txt_item_brand);

        }
    }
}
