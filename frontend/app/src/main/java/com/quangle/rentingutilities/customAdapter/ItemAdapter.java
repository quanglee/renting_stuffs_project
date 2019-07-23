package com.quangle.rentingutilities.customAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemNameView;
        private final TextView itemDescView;
        private final ImageView itemImageView;
        private final TextView itemPriceView;

        private ItemViewHolder(View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.txtName);
            itemDescView = itemView.findViewById(R.id.txtDesc);
            itemPriceView = itemView.findViewById(R.id.txtPrice);
            itemImageView = itemView.findViewById(R.id.itemImage);
        }
    }

    private final LayoutInflater mInflater;
    private List<Item> mItems;
    private Context context;


    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // TODO: click to open details item
        }
    };

    public ItemAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.home_items_content, parent, false);
        return new ItemAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ItemViewHolder holder, int position) {

        if (mItems != null) {
            Item current = mItems.get(position);
            holder.itemNameView.setText(current.getName());
            holder.itemDescView.setText(current.getDescription());
            holder.itemPriceView.setText("$" + String.valueOf(current.getPrice()));

            Picasso.get().load(current.getImageURL()).resize(250, 450)
                    .centerCrop().into(holder.itemImageView);
        }
    }

    public void setNewItems(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }
}


