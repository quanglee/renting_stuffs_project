package com.quangle.rentingutilities.core.model;

import java.io.Serializable;

public class Item implements Serializable {

    //Enum variables
    public static enum CATEGORY{
        CARS("Cars"),
        ELECTRONICS("Electronics"),
        HOME_AND_GARDEN("Home and Garden"),
        OTHERS("Others");

        private String displayName;

        CATEGORY(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    };

    public static enum CONDITION{
        NEW("New"),
        LIKE_NEW("Like new");

        private String displayName;

        CONDITION(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }
    };

    //fields
    private int id, ownerId;
    private String name, description,
            condition, // enum CONDITION
            category, // enum CATEGORY
            imageURL, // comma separate string
            tags;// comma separate string
    private double lat, lng, price;
    private boolean isActive;

    //getter & setter
    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
