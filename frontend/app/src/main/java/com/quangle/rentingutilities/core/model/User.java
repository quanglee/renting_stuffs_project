package com.quangle.rentingutilities.core.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    //user fields
    @SerializedName("uid")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("isAdmin")
    private boolean isAdmin;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("address")
    private String address;
    private String password;
    private int numberOfReview;//Owner reviews Borrower
    private double averageRating;

    //constructor
    public User() {
        id = "";
        email = "";
        username = "";
        address = "";
        isAdmin = false;
        password = "";
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("username", username);
        hashMap.put("address", address);
        hashMap.put("lat", lat);
        hashMap.put("lng", lng);
        if (id.equals(""))
            hashMap.put("password", password);
        return hashMap;
    }

    //getter & setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
