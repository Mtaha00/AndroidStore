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
import com.example.androidstore.model.popular.PopularA;
import com.example.androidstore.model.popular.PopularAmazing;
import com.example.androidstore.model.popular.PopularFirst;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<PopularA> data;

    public PopularAdapter(Context context, List<PopularA> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_popular_amazing, parent, false);
            return new PopularHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_popular_first_amazing, parent, false);
            return new PopularFirstHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            PopularAmazing popularAmazing = (PopularAmazing) data.get(position).getObject();
            ((PopularHolder) holder).setPopularHolder(popularAmazing);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ShowDetailProductActivity.class);
                    intent.putExtra(Key.id,popularAmazing.getId());
                    intent.putExtra(Key.category_id,popularAmazing.getCategory_id());
                    intent.putExtra(Key.title,popularAmazing.getName());
                    intent.putExtra(Key.brand,popularAmazing.getBrand());
                    intent.putExtra(Key.price,popularAmazing.getPrice());
                    intent.putExtra(Key.priceOff,popularAmazing.getOffPrice());
                    intent.putExtra(Key.valueOffer,popularAmazing.getValue_off());
                    intent.putExtra(Key.detail_category_id,popularAmazing.getDetail_category_id());
                    context.startActivity(intent);
                }
            });



        } else {
            PopularFirst popularFirst = (PopularFirst) data.get(position).getObject();
            ((PopularFirstHolder) holder).setFirstPopular(popularFirst);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public class PopularFirstHolder extends RecyclerView.ViewHolder {

        ImageView imgFirstPopular;
        TextView firstTxtPopular;

        public PopularFirstHolder(@NonNull View itemView) {
            super(itemView);

            imgFirstPopular = itemView.findViewById(R.id.img_popularAmazingFirst_item);
            firstTxtPopular = itemView.findViewById(R.id.titleTxt_popularAmazingFirst_item);
        }

        public void setFirstPopular(PopularFirst popularFirst) {
            firstTxtPopular.setText(popularFirst.getName());
            Picasso.get().load(popularFirst.getLink_img()).into(imgFirstPopular);
        }
    }

    public class PopularHolder extends RecyclerView.ViewHolder {

        ImageView imgOffer;
        TextView nameTxt;
        TextView priceOffTxt;
        TextView priceTxt;
        TextView valueOfferTxt;

        public PopularHolder(@NonNull View itemView) {
            super(itemView);

            imgOffer = itemView.findViewById(R.id.img_popularAmazing_item);
            nameTxt = itemView.findViewById(R.id.name_popularAmazing_item);
            priceOffTxt = itemView.findViewById(R.id.txt_priceOff_popularAmazing_item);
            priceTxt = itemView.findViewById(R.id.txt_price_popularAmazing_item);
            valueOfferTxt = itemView.findViewById(R.id.value_popularAmazing_item);

        }

        public void setPopularHolder(PopularAmazing popularHolder) {
            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            String txt_price_dec = decimalFormat.format(Integer.valueOf(popularHolder.getPrice()));
            String txt_off_price_dec = decimalFormat.format(Integer.valueOf(popularHolder.getOffPrice()));

            nameTxt.setText(popularHolder.getName());
            Picasso.get().load(popularHolder.getLink_img()).into(imgOffer);

            if (popularHolder.getOffPrice().equals(popularHolder.getPrice())) {
                priceTxt.setVisibility(View.INVISIBLE);
                priceOffTxt.setText(txt_price_dec + " تومان ");
                valueOfferTxt.setVisibility(View.INVISIBLE);

            } else {
                priceOffTxt.setText(txt_off_price_dec + " تومان ");
                valueOfferTxt.setText(popularHolder.getValue_off() + "%");

                SpannableString spannableString = new SpannableString(txt_price_dec);
                spannableString.setSpan(new StrikethroughSpan(), 0, popularHolder.getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                priceTxt.setText(spannableString);


            }


        }
    }


}
