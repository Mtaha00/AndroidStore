package com.example.androidstore.adapter.DetailCategoryAdapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.activity.ShowDetailProductActivity;
import com.example.androidstore.model.PopularModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class PopularDetailAdapter extends RecyclerView.Adapter<PopularDetailAdapter.Holder>{

    Context context;
    List<PopularModel> popularModelList;

    public PopularDetailAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_popular,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Holder holder, int position) {
        DecimalFormat decimalFormat=new DecimalFormat("###,###");

        String txt_price_dec=decimalFormat.format(Integer.valueOf(popularModelList.get(position).getPrice()));
        String txt_off_price_dec=decimalFormat.format(Integer.valueOf(popularModelList.get(position).getOffPrice()));


        holder.nameTxt.setText(popularModelList.get(position).getName());
        holder.priceOffTxt.setText(txt_off_price_dec+" تومان ");
        holder.valueOfferTxt.setText(popularModelList.get(position).getValue_off()+"%");

        SpannableString spannableString=new SpannableString(txt_price_dec);
        spannableString.setSpan(new StrikethroughSpan(),0,popularModelList.get(position).getPrice().length(),SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.priceTxt.setText(spannableString);

        Picasso.get().load(popularModelList.get(position).getLink_img()).into(holder.imgOffer);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ShowDetailProductActivity.class);
                intent.putExtra(Key.id,popularModelList.get(position).getId());
                intent.putExtra(Key.category_id,popularModelList.get(position).getCategory_id());
                intent.putExtra(Key.title,popularModelList.get(position).getName());
                intent.putExtra(Key.brand,popularModelList.get(position).getBrand());
                intent.putExtra(Key.price,popularModelList.get(position).getPrice());
                intent.putExtra(Key.priceOff,popularModelList.get(position).getOffPrice());
                intent.putExtra(Key.valueOffer,popularModelList.get(position).getValue_off());
                intent.putExtra(Key.detail_category_id,popularModelList.get(position).getDetail_category_id());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView imgOffer;
        TextView nameTxt;
        TextView priceOffTxt;
        TextView priceTxt;
        TextView valueOfferTxt;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imgOffer=itemView.findViewById(R.id.img_popular_item);
            nameTxt=itemView.findViewById(R.id.name_popular_item);
            priceOffTxt=itemView.findViewById(R.id.txt_priceOff_popular_item);
            priceTxt=itemView.findViewById(R.id.txt_price_popular_item);
            valueOfferTxt=itemView.findViewById(R.id.valueOffer_popular_item);

        }
    }
}
