package com.example.androidstore.adapter.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.Global.Key;
import com.example.androidstore.R;
import com.example.androidstore.activity.ShowDetailProductActivity;
import com.example.androidstore.model.Amazing.Amazing;
import com.example.androidstore.model.Amazing.AmazingFirst;
import com.example.androidstore.model.Amazing.AmazingOffer;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AmazingProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<Amazing> data;

    public AmazingProductAdapter(Context context, List<Amazing> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item for amazing offer
        if (viewType ==0){
            View view=LayoutInflater.from(context).inflate(R.layout.item_offer,parent,false);
            return new AmazingOfferHolder(view);
        }else{
            View view=LayoutInflater.from(context).inflate(R.layout.item_first_offer,parent,false);
            return new FirstAmazingHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            AmazingOffer amazingOffer= (AmazingOffer) data.get(position).getObject();
            ((AmazingOfferHolder)holder).setAmazingOffer(amazingOffer);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, ShowDetailProductActivity.class);
                    intent.putExtra(Key.id,amazingOffer.getId());
                    intent.putExtra(Key.category_id,amazingOffer.getCategory_id());
                    intent.putExtra(Key.title,amazingOffer.getName());
                    intent.putExtra(Key.brand,amazingOffer.getBrand());
                    intent.putExtra(Key.price,amazingOffer.getPrice());
                    intent.putExtra(Key.priceOff,amazingOffer.getOffPrice());
                    intent.putExtra(Key.valueOffer,amazingOffer.getValue_off());
                    intent.putExtra(Key.detail_category_id,amazingOffer.getDetail_category_id());
                    context.startActivity(intent);
                }
            });


        }else{
            AmazingFirst amazingFirst= (AmazingFirst) data.get(position).getObject();
            ((FirstAmazingHolder)holder).setFirstAmazing(amazingFirst);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public static class FirstAmazingHolder extends RecyclerView.ViewHolder{

        ImageView imgFirstOffer;
        TextView txtFirstOffer;

        public FirstAmazingHolder(@NonNull View itemView) {
            super(itemView);

            imgFirstOffer=itemView.findViewById(R.id.img_first_offer);
            txtFirstOffer=itemView.findViewById(R.id.text_first_offer);
        }
        public void setFirstAmazing(AmazingFirst amazingFirst){
            txtFirstOffer.setText(amazingFirst.getTitle());
            Picasso.get().load(amazingFirst.getLink_img()).into(imgFirstOffer);
        }
    }

    public static class AmazingOfferHolder extends RecyclerView.ViewHolder{

        ImageView imgOffer;
        TextView nameTxt;
        TextView priceOffTxt;
        TextView priceTxt;
        TextView valueOfferTxt;


        public AmazingOfferHolder(@NonNull View itemView) {
            super(itemView);

            imgOffer=itemView.findViewById(R.id.img_offer_item);
            nameTxt=itemView.findViewById(R.id.name_offer_item);
            priceOffTxt=itemView.findViewById(R.id.txt_priceOff_offer_item);
            priceTxt=itemView.findViewById(R.id.txt_price_offer_item);
            valueOfferTxt=itemView.findViewById(R.id.value_offer_item);




        }
        public void setAmazingOffer(AmazingOffer amazingOffer){

            DecimalFormat decimalFormat=new DecimalFormat("###,###");
            String txt_price_dec=decimalFormat.format(Integer.valueOf(amazingOffer.getPrice()));
            String txt_off_price_dec=decimalFormat.format(Integer.valueOf(amazingOffer.getOffPrice()));


            nameTxt.setText(amazingOffer.getName());
            priceOffTxt.setText(txt_off_price_dec+" تومان ");
            valueOfferTxt.setText(amazingOffer.getValue_off()+"%");

            SpannableString spannableString=new SpannableString(txt_price_dec);
            spannableString.setSpan(new StrikethroughSpan(),0,amazingOffer.getPrice().length(),SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            priceTxt.setText(spannableString);

            Picasso.get().load(amazingOffer.getLink_img()).into(imgOffer);

        }
    }

}
