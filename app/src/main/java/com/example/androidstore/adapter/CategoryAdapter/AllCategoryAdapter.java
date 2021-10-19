package com.example.androidstore.adapter.CategoryAdapter;

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

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.Holder> {

    List<Category> categoryList;
    Context context;

    public AllCategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_all_category,parent,false);

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
            imageView=itemView.findViewById(R.id.img_item_allCategory);
            title=itemView.findViewById(R.id.nameTxt_item_allCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (setOnItemClickListener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnItemClickListener.onItemClick(getAdapterPosition(),categoryList.get(getAdapterPosition()));

                    }
                }
            });
        }
    }
    SetOnItemClickListener setOnItemClickListener;

    public interface SetOnItemClickListener{
        void onItemClick(int position ,Category category );
    }
    public void onItemClickListener(SetOnItemClickListener setOnItemClickListener){
        this.setOnItemClickListener=setOnItemClickListener;
    }

}
