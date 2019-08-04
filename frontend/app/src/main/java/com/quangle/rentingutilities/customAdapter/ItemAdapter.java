package com.quangle.rentingutilities.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.core.model.Wishlist;
import com.quangle.rentingutilities.networking.Api;
import com.quangle.rentingutilities.networking.RetrofitService;
import com.quangle.rentingutilities.ui.BaseActivity;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.utils.MyFirebaseMessagingService;
import com.quangle.rentingutilities.utils.OnClickListener;
import com.quangle.rentingutilities.viewmodel.UserViewModel;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemNameView;
        private final TextView itemDescView;
        private final ImageView itemImageView;
        private final TextView itemPriceView;
        private final Switch switchWishlist;
        private FirebaseAuth firebaseAuth;
        private Api api;

        private ItemViewHolder(View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.txtName);
            itemDescView = itemView.findViewById(R.id.txtDesc);
            itemPriceView = itemView.findViewById(R.id.txtPrice);
            itemImageView = itemView.findViewById(R.id.itemImage);

            switchWishlist = itemView.findViewById(R.id.switchWishlist);

            if (api == null) {
                api = RetrofitService.get();
            }

            if (firebaseAuth ==null) {
                firebaseAuth = FirebaseAuth.getInstance();
            }

        }
    }

    private final LayoutInflater mInflater;
    private List<Item> mItems;
    private OnClickListener<Item> listener;
    private Context context;

    public ItemAdapter(Context context, OnClickListener<Item> listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.home_items_content, parent, false);

        context = parent.getContext();
        return new ItemAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ItemViewHolder holder, int position) {
        if (mItems != null) {
            Item current = mItems.get(position);
            holder.itemNameView.setText(current.getName());
            holder.itemDescView.setText(current.getDescription());
            holder.itemPriceView.setText("$" + String.valueOf(current.getPrice()));
            holder.itemView.setOnClickListener(v -> {
                listener.onClick(current);
            });

            Picasso.get().load(current.getImageURL()).resize(1000, 500).onlyScaleDown()
                    .into(holder.itemImageView);

            //set up switchWishlist
            if(Helper.isUserLoggedIn() == false){//guest
                System.out.println("GUEST");
                holder.switchWishlist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        //redirect to Login
                        BaseActivity.startHomeActivityAtTab(R.id.login);
                    }
                });

            }else{//logged-in user

                //hide if is the owner
                if(Helper.isLoggedInUserEmailMatch(current.getOwnerId())){
                    holder.switchWishlist.setVisibility(View.GONE);
                }else{
                    holder.switchWishlist.setVisibility(View.VISIBLE);

                    holder.switchWishlist.setOnCheckedChangeListener(null);//clear previous listener

                    //set current state of wishlist
                    holder.switchWishlist.setChecked(false);

                    for(Item item : UserViewModel.wishlistItemOfLoggedInUser){
                        if(item.getId() == current.getId())
                            holder.switchWishlist.setChecked(true);
                    }

                    holder.switchWishlist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            holder.switchWishlist.setEnabled(false);

                            holder.firebaseAuth.getCurrentUser().getIdToken(false).addOnSuccessListener(getTokenResult -> {

                                HashMap<String, Object> params = new HashMap<>();
                                params.put("itemId", current.getId());
                                params.put("fcmToken", MyFirebaseMessagingService.getToken(context));
                                if(isChecked) {//add

                                    Call<Wishlist> wishlistCall = holder.api.createWishlist(getTokenResult.getToken(), params);
                                    wishlistCall.enqueue(new Callback<Wishlist>() {
                                        @Override
                                        public void onResponse(Call<Wishlist> call, Response<Wishlist> response) {

                                            System.out.println("ADD WISHLIST SUCCESS");
                                            //update local UserWishList
                                            UserViewModel.wishlistItemOfLoggedInUser.add(current);
                                            holder.switchWishlist.setEnabled(true);
                                        }

                                        @Override
                                        public void onFailure(Call<Wishlist> call, Throwable t) {
                                            holder.switchWishlist.setChecked(false);//return previous state
                                            holder.switchWishlist.setEnabled(true);
                                            System.out.println("ADD WISHLIST ON FAILURE");
                                        }
                                    });
                                }else{//delete
                                    Call<Wishlist> wishlistCall = holder.api.deleteWishlist(getTokenResult.getToken(), params);
                                    wishlistCall.enqueue(new Callback<Wishlist>() {
                                        @Override
                                        public void onResponse(Call<Wishlist> call, Response<Wishlist> response) {

                                            System.out.println("DELETE WISHLIST SUCCESS");
                                            //update local UserWishList
                                            UserViewModel.wishlistItemOfLoggedInUser.remove(current);

                                            for(Item item : UserViewModel.wishlistItemOfLoggedInUser){
                                                if(item.getId() == current.getId())
                                                    UserViewModel.wishlistItemOfLoggedInUser.remove(item);
                                            }

                                            holder.switchWishlist.setEnabled(true);
                                        }

                                        @Override
                                        public void onFailure(Call<Wishlist> call, Throwable t) {
                                            holder.switchWishlist.setChecked(false);//return previous state
                                            holder.switchWishlist.setEnabled(true);
                                            System.out.println("DELETE WISHLIST ON FAILURE");
                                        }
                                    });
                                }
                            });

                        }
                    });
                }
            }
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


