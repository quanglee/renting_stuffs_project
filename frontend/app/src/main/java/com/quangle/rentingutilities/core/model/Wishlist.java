package com.quangle.rentingutilities.core.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Wishlist implements Serializable {

    @SerializedName("itemId")
    private int itemId;
    @SerializedName("ownerId")
    private String ownerId;
    @SerializedName("endDate")
    private String endDate;

    public Wishlist(int itemId) {
        this.itemId = itemId;
        ownerId = "";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        endDate = format1.format(calendar.getTime());
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("itemId", itemId);
        hashMap.put("ownerId", ownerId);
        hashMap.put("endDate", endDate);
        return hashMap;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
