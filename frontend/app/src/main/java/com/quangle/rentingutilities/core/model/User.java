package com.quangle.rentingutilities.core.model;

import java.io.Serializable;

public class User implements Serializable {

    //user fields
    private int id;
    private String email, username;
    private int numberOfReview;//Owner reviews Borrower
    private double lat, lng, averageRating;
    private boolean isActive, isAdmin;

    //constructor
    public User() {}

    //getter & setter
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
