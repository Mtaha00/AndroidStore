package com.example.androidstore.adapter.ProductAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.model.detailProduct.CompareModel;

import java.util.List;

public class CompareProductAdapter extends RecyclerView.Adapter<CompareProductAdapter.Holder> {

    Context context;
    List<CompareModel> compareFirstList;
    List<CompareModel> compareSecondList;



    public CompareProductAdapter(Context context, List<CompareModel> compareFirstList, List<CompareModel> compareSecondList) {
        this.context = context;
        this.compareFirstList = compareFirstList;
        this.compareSecondList = compareSecondList;
    }

    @NonNull

    @Override
    public Holder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_compare_product,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        holder.title.setText(compareFirstList.get(position).getTitle());
        holder.detail1.setText(compareFirstList.get(position).getText());
        holder.detail2.setText(compareSecondList.get(position).getText());


    }

    @Override
    public int getItemCount() {
        return compareFirstList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title;
        TextView detail1;
        TextView detail2;

        public Holder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title_compare_product);
            detail1=itemView.findViewById(R.id.firstDetail_compare_product);
            detail2=itemView.findViewById(R.id.secondDetail_compare_product);

        }
    }

}
