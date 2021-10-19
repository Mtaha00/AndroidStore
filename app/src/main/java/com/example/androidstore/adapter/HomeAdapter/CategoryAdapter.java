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
import com.example.androidstore.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    List<Category> categoryList;
    Context context;


    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Category category=categoryList.get(position);
        holder.title.setText(category.getTitle());
        Picasso.get().load(categoryList.get(position).getLink_img()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_category_item);
            title=itemView.findViewById(R.id.title_category_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (setOnItemClickListener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnItemClickListener.onCategoryClickItem(getAdapterPosition(),categoryList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    SetOnItemClickListener setOnItemClickListener;

    public interface SetOnItemClickListener{
        void onCategoryClickItem(int position,Category category);
    }
    public void onItemClickListener(SetOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener=setOnItemClickListener;
    }

}
