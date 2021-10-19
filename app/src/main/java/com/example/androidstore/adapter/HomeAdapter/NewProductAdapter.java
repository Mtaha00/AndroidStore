package com.example.androidstore.adapter.HomeAdapter;

import android.content.Context;
import android.content.Intent;
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

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.Holder> {
    Context context;
    List<Product> products;

    public NewProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_product, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String price = decimalFormat.format(Integer.valueOf(products.get(position).getOffPrice()));
        holder.priceTxt.setText(price + "تومان");
        holder.title.setText(products.get(position).getName());
        Picasso.get().load(products.get(position).getLink_img()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetailProductActivity.class);
                intent.putExtra(Key.id, products.get(position).getId());
                intent.putExtra(Key.category_id, products.get(position).getCategory_id());
                intent.putExtra(Key.title, products.get(position).getName());
                intent.putExtra(Key.brand, products.get(position).getBrand());
                intent.putExtra(Key.price, products.get(position).getPrice());
                intent.putExtra(Key.priceOff, products.get(position).getOffPrice());
                intent.putExtra(Key.valueOffer, products.get(position).getValue_off());
                intent.putExtra(Key.detail_category_id, products.get(position).getDetail_category_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView priceTxt;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_newProduct_item);
            priceTxt = itemView.findViewById(R.id.txt_price_new_item);
            title = itemView.findViewById(R.id.name_newProduct_item);

        }
    }

}
