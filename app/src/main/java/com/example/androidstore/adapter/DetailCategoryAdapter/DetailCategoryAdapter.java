package com.example.androidstore.adapter.DetailCategoryAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.model.DetailCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailCategoryAdapter extends RecyclerView.Adapter<DetailCategoryAdapter.Holder> {

    List<DetailCategory> detailCategoryList;
    Context context;

    public DetailCategoryAdapter(List<DetailCategory> categoryList, Context context) {
        this.detailCategoryList = categoryList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_category, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DetailCategory detailCategory = detailCategoryList.get(position);
        holder.title.setText(detailCategory.getName());
        Picasso.get().load(detailCategoryList.get(position).getLink_img()).into(holder.imageView);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ShowCategoryDetailProduct.class);
//                intent.putExtra(Key.title, detailCategory.getName());
//                intent.putExtra(Key.id, detailCategory.getId());
//               context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return detailCategoryList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_item_detailCategory);
            title = itemView.findViewById(R.id.nameTxt_item_detailCategory);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (setOnItemClick!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnItemClick.onClick(getAdapterPosition(),detailCategoryList.get(getAdapterPosition()));
                    }
                }
            });


        }
    }

    SetOnItemClick setOnItemClick;

    public interface SetOnItemClick {
        void onClick(int position, DetailCategory detailCategory);
    }

    public void setOnItemClickListener(SetOnItemClick setOnItemClick) {
        this.setOnItemClick = setOnItemClick;
    }

}
