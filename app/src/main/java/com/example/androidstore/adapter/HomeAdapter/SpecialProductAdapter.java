package com.example.androidstore.adapter.HomeAdapter;

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
import com.example.androidstore.model.SpecialProduct.Special;
import com.example.androidstore.model.SpecialProduct.SpecialProduct;
import com.example.androidstore.model.SpecialProduct.SpecialProductFirst;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SpecialProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Special> data;

    public SpecialProductAdapter(Context context, List<Special> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_special_product, parent, false);
            return new SpecialProductHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_special_product_first, parent, false);
            return new FirstSpecialProductHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == 0) {
            SpecialProduct specialProduct = (SpecialProduct) data.get(position).getObject();
            ((SpecialProductHolder) holder).setSpecialProduct(specialProduct);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShowDetailProductActivity.class);
                    intent.putExtra(Key.id, specialProduct.getId());
                    intent.putExtra(Key.category_id, specialProduct.getCategory_id());
                    intent.putExtra(Key.title, specialProduct.getName());
                    intent.putExtra(Key.brand, specialProduct.getBrand());
                    intent.putExtra(Key.price, specialProduct.getPrice());
                    intent.putExtra(Key.priceOff, specialProduct.getOffPrice());
                    intent.putExtra(Key.valueOffer, specialProduct.getValue_off());
                    intent.putExtra(Key.detail_category_id, specialProduct.getDetail_category_id());
                    context.startActivity(intent);
                }
            });


        } else {
            SpecialProductFirst specialProductFirst = (SpecialProductFirst) data.get(position).getObject();
            ((FirstSpecialProductHolder) holder).setFirstSpecial(specialProductFirst);
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

    public static class FirstSpecialProductHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageFirst;

        public FirstSpecialProductHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt_firstSpecial_item);
            imageFirst = itemView.findViewById(R.id.img_firstSpecial_item);
        }

        public void setFirstSpecial(SpecialProductFirst firstSpecial) {
            title.setText(firstSpecial.getTitle());
            Picasso.get().load(firstSpecial.getLink_img()).into(imageFirst);
        }
    }

    public static class SpecialProductHolder extends RecyclerView.ViewHolder {
        ImageView imgOffer;
        TextView nameTxt;
        TextView priceOffTxt;
        TextView priceTxt;
        TextView valueOfferTxt;

        public SpecialProductHolder(@NonNull View itemView) {
            super(itemView);

            nameTxt = itemView.findViewById(R.id.name_special_item);
            priceTxt = itemView.findViewById(R.id.txt_price_special_item);
            priceOffTxt = itemView.findViewById(R.id.txt_priceOff_special_item);
            valueOfferTxt = itemView.findViewById(R.id.value_special_item);
            imgOffer = itemView.findViewById(R.id.img_special_item);
        }

        public void setSpecialProduct(SpecialProduct specialProduct) {

            DecimalFormat decimalFormat = new DecimalFormat("###,###");
            String txt_price_dec = decimalFormat.format(Integer.valueOf(specialProduct.getPrice()));
            String txt_off_price_dec = decimalFormat.format(Integer.valueOf(specialProduct.getOffPrice()));

            nameTxt.setText(specialProduct.getName());

            if (specialProduct.getOffPrice().equals(specialProduct.getPrice())) {
                priceTxt.setVisibility(View.INVISIBLE);
                priceOffTxt.setText(txt_price_dec + " تومان ");
                valueOfferTxt.setVisibility(View.INVISIBLE);

            }
            else {
                priceOffTxt.setText(txt_off_price_dec + " تومان ");
                valueOfferTxt.setText(specialProduct.getValue_off() + "%");

                SpannableString spannableString = new SpannableString(txt_price_dec);
                spannableString.setSpan(new StrikethroughSpan(), 0, specialProduct.getPrice().length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                priceTxt.setText(spannableString);


            }
            Picasso.get().load(specialProduct.getLink_img()).into(imgOffer);
        }
    }

}
