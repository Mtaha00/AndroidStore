package com.example.androidstore.adapter.ProductAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.model.detailProduct.ReviewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Holder>{

    Context context;
    List<ReviewModel> data;

    public ReviewAdapter(Context context, List<ReviewModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_review_product,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.text.setText(data.get(position).getText());
        Picasso.get().load(data.get(position).getLink_img()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title;
        TextView text;
        ImageView imageView;


        public Holder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title_review_product_item);
            text=itemView.findViewById(R.id.text_review_product_item);
            imageView=itemView.findViewById(R.id.img_review_product_item);

        }
    }
}
