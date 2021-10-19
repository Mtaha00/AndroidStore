package com.example.androidstore.adapter.ShowListProductAdapter;

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
import com.example.androidstore.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class DetailCategoryProductAdapter extends RecyclerView.Adapter<DetailCategoryProductAdapter.Holder> {

    Context context;
    List<Product> productList;

    public DetailCategoryProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_product_detail, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String txt_price_dec = decimalFormat.format(Integer.valueOf(productList.get(position).getPrice()));
        String txt_off_price_dec = decimalFormat.format(Integer.valueOf(productList.get(position).getOffPrice()));

        holder.nameTxt.setText(productList.get(position).getName());


        if (productList.get(position).getPrice().equals(productList.get(position).getOffPrice())) {
            holder.priceTxt.setVisibility(View.INVISIBLE);
            holder.priceOffTxt.setText(txt_price_dec + " تومان ");
            holder.valueOfferTxt.setVisibility(View.INVISIBLE);

        } else {
            holder.priceOffTxt.setText(txt_off_price_dec + " تومان ");
            holder.valueOfferTxt.setText(productList.get(position).getValue_off() + "%");

            SpannableString spannableString = new SpannableString(txt_price_dec);
            spannableString.setSpan(new StrikethroughSpan(), 0, productList.get(position).getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.priceTxt.setText(spannableString);

        }
        Picasso.get().load(productList.get(position).getLink_img()).into(holder.imgOffer);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetailProductActivity.class);
                intent.putExtra(Key.id, productList.get(position).getId());
                intent.putExtra(Key.category_id, productList.get(position).getCategory_id());
                intent.putExtra(Key.title, productList.get(position).getName());
                intent.putExtra(Key.brand, productList.get(position).getBrand());
                intent.putExtra(Key.price, productList.get(position).getPrice());
                intent.putExtra(Key.priceOff, productList.get(position).getOffPrice());
                intent.putExtra(Key.valueOffer, productList.get(position).getValue_off());
                intent.putExtra(Key.detail_category_id, productList.get(position).getDetail_category_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        ImageView imgOffer;
        TextView nameTxt;
        TextView priceOffTxt;
        TextView priceTxt;
        TextView valueOfferTxt;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imgOffer = itemView.findViewById(R.id.img_product_item);
            nameTxt = itemView.findViewById(R.id.name_product_item);
            priceOffTxt = itemView.findViewById(R.id.txt_priceOff_product_item);
            priceTxt = itemView.findViewById(R.id.txt_price_product_item);
            valueOfferTxt = itemView.findViewById(R.id.valueOffer_product_item);

        }
    }
}

