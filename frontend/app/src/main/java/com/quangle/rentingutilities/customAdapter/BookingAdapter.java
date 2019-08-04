package com.quangle.rentingutilities.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.utils.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemNameView;
        private final TextView itemDescView;
        private final ImageView itemImageView;
        private final TextView itemPriceView;
        private final TextView itemStatusView;


        private ItemViewHolder(View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.txtName);
            itemDescView = itemView.findViewById(R.id.txtDesc);
            itemPriceView = itemView.findViewById(R.id.txtPrice);
            itemImageView = itemView.findViewById(R.id.itemImage);
            itemStatusView = itemView.findViewById(R.id.txtStatus);
        }
    }

    private final LayoutInflater mInflater;
    private List<Booking> mBookings;
    private Context context;
    private OnClickListener<Booking> listener;

    public BookingAdapter(Context context, OnClickListener<Booking> listener) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.booking_items_content, parent, false);
        return new BookingAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingAdapter.ItemViewHolder holder, int position) {

        if (mBookings != null && mBookings.get(position).getItem() != null) {
            Booking current = mBookings.get(position);
            holder.itemNameView.setText(current.getItem().getName());
            holder.itemDescView.setText(current.getItem().getDescription());
            holder.itemPriceView.setText("$" + String.valueOf(current.getItem().getPrice()));
            holder.itemStatusView.setText(current.getStatus());
            Picasso.get().load(current.getItem().getImageURL()).resize(1000, 600).onlyScaleDown()
                    .into(holder.itemImageView);

            // click to open BookingDetailFragment
            holder.itemView.setOnClickListener(v -> {
                listener.onClick(current);
            });
        }
    }

    public void setNewBookings(List<Booking> bookings) {
        mBookings = bookings;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mBookings != null)
            return mBookings.size();
        else return 0;
    }
}


