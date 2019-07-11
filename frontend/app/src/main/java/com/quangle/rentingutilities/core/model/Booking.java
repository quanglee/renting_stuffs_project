package com.quangle.rentingutilities.core.model;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {

    //Enum variables
    public static enum STATUS{

        PENDING("Pending"),
        ACCEPTED("Accepted"),
        REJECTED("Rejected");

        private String displayName;

        STATUS(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    };

    //fields
    private int id, itemId, borrowerId;
    private Date startDate, returnDate;
    private String status;

    //constructor
    public Booking() {}

    //getter & setter
    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
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

}
