package com.quangle.rentingutilities.core.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    //Borrower review Item (ownerId = -1)
    //Owner review Borrower (itemId = -1), risk: hard to find corresponding booking

    //fields
    @SerializedName("id")
    private int id;

    @SerializedName("ownerId")
    private String ownerId;

    @SerializedName("borrowerId")
    private String borrowerId;

    @SerializedName("itemId")
    private String itemId;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("rating")
    private double rating;

    @SerializedName("bookingId")
    private double bookingId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getBookingId() {
        return bookingId;
    }

    public void setBookingId(double bookingId) {
        this.bookingId = bookingId;
    }
}
