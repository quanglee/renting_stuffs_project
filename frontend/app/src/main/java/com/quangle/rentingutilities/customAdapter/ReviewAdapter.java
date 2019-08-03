package com.quangle.rentingutilities.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Booking;
import com.quangle.rentingutilities.utils.OnClickListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemTitleView;
        private final TextView itemContentView;
        private final ImageView itemImageUserView;
        private final RatingBar itemRatingBarView;


        private ItemViewHolder(View itemView) {
            super(itemView);
            itemTitleView = itemView.findViewById(R.id.txtTitle);
            itemContentView = itemView.findViewById(R.id.txtDesc);
            itemImageUserView = itemView.findViewById(R.id.imageUser);
            itemRatingBarView = itemView.findViewById(R.id.ratingBar);

        }
    }

    private final LayoutInflater mInflater;
    private List<JsonObject> mReviews;
    private Context context;

    public ReviewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.review_items_content, parent, false);
        return new ReviewAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ItemViewHolder holder, int position) {

        if (mReviews != null) {
            JsonObject jsonObject = mReviews.get(position);
            String title = jsonObject.get("title").getAsString();
            String content = jsonObject.get("content").getAsString();
            float stars = Float.parseFloat(jsonObject.get("rating").getAsString());
            holder.itemTitleView.setText(title);
            holder.itemContentView.setText(content);
            holder.itemRatingBarView.setRating(stars);
            // TODO: should be load user photo
            Picasso.get().load("https://picsum.photos/id/172/320/320").resize(250, 250).onlyScaleDown()
                    .into(holder.itemImageUserView);

        }
    }

    public void setNewReviews(List<JsonObject> reviews) {
        mReviews = reviews;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mReviews != null)
            return mReviews.size();
        else return 0;
    }
}


