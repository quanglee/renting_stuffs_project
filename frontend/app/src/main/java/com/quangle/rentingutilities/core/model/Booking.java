package com.quangle.rentingutilities.core.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Booking implements Serializable {

    //Enum variables
    public static enum STATUS{

        PENDING("Pending"),//Borrower requests a Booking
        ACCEPTED("Accepted"),//Owner accepts a Booking
        REJECTED("Rejected"),//Owner rejects a Booking
        DONE("Done");//when item is returned

        private String displayName;

        STATUS(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    };

    //fields
    @SerializedName("id")
    private int id;

    @SerializedName("itemId")
    private int itemId;

    @SerializedName("borrowerId")
    private String borrowerId;

    @SerializedName("startDate")
    private Date startDate;

    @SerializedName("returnDate")
    private Date returnDate;

    @SerializedName("status")
    private String status;

    private Item item;

    //constructor
    public Booking() {}

    public HashMap<String, Object> toHashMap() {
        SimpleDateFormat saveDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("itemId", itemId);
        hashMap.put("borrowerId", borrowerId);
        hashMap.put("status", status);
        hashMap.put("startDate", saveDateFormat.format(startDate));
        hashMap.put("returnDate", saveDateFormat.format(returnDate));

        return hashMap;
    }

    //getter & setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
