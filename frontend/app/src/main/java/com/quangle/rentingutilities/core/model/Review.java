package com.quangle.rentingutilities.core.model;

import java.io.Serializable;

public class Review implements Serializable {

    //Borrower review Item (ownerId = -1)
    //Owner review Borrower (itemId = -1), risk: hard to find corresponding booking

    //fields
    private int id,
            ownerId,
            borrowerId,
            itemId;

    private String title, content;
    private double rating;//from 0 - 5

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
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
}
