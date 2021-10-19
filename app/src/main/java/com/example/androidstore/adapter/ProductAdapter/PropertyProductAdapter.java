package com.example.androidstore.adapter.ProductAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.model.detailProduct.PropertyProduct;
import com.example.androidstore.R;

import java.util.List;

public class PropertyProductAdapter extends RecyclerView.Adapter<PropertyProductAdapter.Holder> {
    Context context;
    List<PropertyProduct> data;

    public PropertyProductAdapter(Context context, List<PropertyProduct> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_property_product,parent,false);
        return new Holder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.titleTxt.setText(data.get(position).getTitle()+" : ");
        holder.detailTxt.setText(data.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{


        TextView detailTxt;
        TextView titleTxt;

        public Holder(@NonNull View itemView) {
            super(itemView);

            titleTxt=itemView.findViewById(R.id.title_property_product_item);
            detailTxt=itemView.findViewById(R.id.detail_property_product_item);


        }
    }

}
