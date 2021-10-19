package com.example.androidstore.adapter.ProductAdapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.activity.CompareProductActivity;
import com.example.androidstore.activity.ShowDetailProductActivity;
import com.example.androidstore.model.SimilarModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class CompareSimilarAdapter extends RecyclerView.Adapter<CompareSimilarAdapter.Holder> {
    Context context;
    List<SimilarModel> data;

    public CompareSimilarAdapter(Context context, List<SimilarModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_compare_similar_product, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String txt_price_dec = decimalFormat.format(Integer.valueOf(data.get(position).getPrice()));
        String txt_off_price_dec = decimalFormat.format(Integer.valueOf(data.get(position).getOffPrice()));

        holder.nameTxt.setText(data.get(position).getName());


        if (data.get(position).getPrice().equals(data.get(position).getOffPrice())) {
            holder.priceTxt.setVisibility(View.INVISIBLE);
            holder.priceOffTxt.setText(txt_price_dec + " تومان ");
            holder.valueOfferTxt.setVisibility(View.INVISIBLE);

        } else {
            holder.priceOffTxt.setText(txt_off_price_dec + " تومان ");
            holder.valueOfferTxt.setText(data.get(position).getValue_off() + "%");

            SpannableString spannableString = new SpannableString(txt_price_dec);
            spannableString.setSpan(new StrikethroughSpan(), 0, data.get(position).getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.priceTxt.setText(spannableString);

        }
        Picasso.get().load(data.get(position).getLink_img()).into(holder.imgOffer);



//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, CompareProductActivity.class);
//                intent.putExtra(Key.id_second, data.get(position).getId());
//                intent.putExtra(Key.category_id_second, data.get(position).getCategory_id());
//                intent.putExtra(Key.title_second, data.get(position).getName());
//                intent.putExtra(Key.brand_second, data.get(position).getBrand());
//                intent.putExtra(Key.price_second, data.get(position).getPrice());
//                intent.putExtra(Key.priceOff_second, data.get(position).getOffPrice());
//                intent.putExtra(Key.valueOffer_second, data.get(position).getValue_off());
//                intent.putExtra(Key.detail_category_id_second, data.get(position).getDetail_category_id());
//                intent.putExtra(Key.linkImg_second, data.get(position).getLink_img());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imgOffer;
        TextView nameTxt;
        TextView priceOffTxt;
        TextView priceTxt;
        TextView valueOfferTxt;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imgOffer = itemView.findViewById(R.id.img_compare_product_item);
            nameTxt = itemView.findViewById(R.id.name_compare_product_item);
            priceOffTxt = itemView.findViewById(R.id.txt_priceOff_compare_product_item);
            priceTxt = itemView.findViewById(R.id.txt_price_compare_product_item);
            valueOfferTxt = itemView.findViewById(R.id.valueOffer_compare_product_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (setOnClickListener!=null&& getAdapterPosition()!=RecyclerView.NO_POSITION){
                        setOnClickListener.onItemClick(getAdapterPosition(),data.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    SetOnClickListener setOnClickListener;

    public interface SetOnClickListener{
        void onItemClick(int position,SimilarModel similar);
    }
    public void onItemClickListener(SetOnClickListener setOnClickListener){
        this.setOnClickListener=setOnClickListener;
    }

}
