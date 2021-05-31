package com.example.merge.product.info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.merge.R;
import com.example.merge.entities.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ViewHolder> {

    @NonNull
    private List<ProductInfoAttribute> attributes = new ArrayList<>();

    public void setProductInfo(ProductInfo info) {
        this.attributes = ProductInfoAttribute.attributesFromProductInfo(info);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_info_attribute_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductInfoAttribute attribute = this.attributes.get(position);

        holder.setName(attribute.getName());
        holder.setValue(attribute.getValue());
        holder.setUnit(attribute.getUnit());
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setName(String name) {
            TextView nameTextView = itemView.findViewById(R.id.attribute_name);
            nameTextView.setText(name);
        }

        public void setValue(String value) {
            TextView valueTextView = itemView.findViewById(R.id.attribute_value);
            valueTextView.setText(value);
        }

        public void setUnit(String unit) {
            TextView unitTextView = itemView.findViewById(R.id.attribute_unit);
            unitTextView.setText(unit);
        }
    }
}
