package com.example.androidstore.adapter.ProductAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidstore.R;
import com.example.androidstore.model.detailProduct.SpecificationModel;
import com.google.android.material.transition.Hold;

import org.w3c.dom.Text;

import java.util.List;

public class SpecificationProductAdapter extends RecyclerView.Adapter<SpecificationProductAdapter.Holder>{

    Context context;
    List<SpecificationModel> data;

    public SpecificationProductAdapter(Context context, List<SpecificationModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_specifications_product,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title.setText(data.get(position).getTitle()+" : ");
        holder.text.setText(data.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView title;
        TextView text;

        public Holder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title_specification_item);
            text=itemView.findViewById(R.id.txt_specification_item);

        }
    }

}
