package com.quangle.rentingutilities.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.quangle.rentingutilities.networking.RetrofitService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    //Enum variables
    public static enum CATEGORY{
        KIDS("Kids/baby"),
        ENTERTAINMENT("Entertainment/hobby"),
        INSTRUMENT("Instrument"),
        INTERIOR("Interior/essential goods"),
        SMARTPHONE("Smartphone/electricity/camera"),
        HANDMADE("Handmade"),
        SPORTS("Sports/outdoor"),
        BICYCLE("Bicycle/Bike"),
        OTHERS("Others");

        private String displayName;

        CATEGORY(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

        public static List<String> valuesList() {
            CATEGORY[] categories = CATEGORY.values();
            List<String> values = new ArrayList<>();

            for (int i = 0; i < categories.length; i++)
                values.add(categories[i].displayName());

            return values;
        }
    };

    public static enum CONDITION{
        COMPLETLE_NEW("Completely new"),
        ALMOST_NEW("Almost new"),
        NO_REMARKABLE_DAMAGE("No remarkable damage"),
        A_BIT_DAMAGED("A bit damaged"),
        DAMAGED("Damaged");

        private String displayName;

        CONDITION(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        // Optionally and/or additionally, toString.
        @Override public String toString() { return displayName; }

        public static List<String> valuesList() {
            CONDITION[] conditions = CONDITION.values();
            List<String> values = new ArrayList<>();

            for (int i = 0; i < conditions.length; i++)
                values.add(conditions[i].displayName());

            return values;
        }
    };

    @SerializedName("id")
    private int id;

    @SerializedName("ownerId")
    private String ownerId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("condition")
    private String condition; // enum CONDITION

    @SerializedName("category")
    private String category; // enum CATEGORY

    @SerializedName("imageURLs")
    private String imageURL; // comma separate string

    @SerializedName("tags")
    private String tags; // comma separate string

    @SerializedName("numberOfReview")
    private int numberOfReview; //Borrower reviews Item

    @SerializedName("averageRating")
    private double averageRating;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("price")
    private double price;

    @SerializedName("isActive")
    private boolean isActive;

    public Item() {
        id = -1;
        ownerId = "";
        name = "";
        description = "";
        condition = CONDITION.COMPLETLE_NEW.toString();
        category = CATEGORY.OTHERS.toString();
        imageURL = "";
        tags = "";
        numberOfReview = 0;
        averageRating = 0;
        lat = 0;
        lng = 0;
        isActive = true;
    }

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
        if (imageURL.equals(""))
            return "";
        else if (imageURL.contains("https"))
            return imageURL;
        else
            return RetrofitService.BASE_URL + imageURL.substring(1);
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

    public int getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(int numberOfReview) {
        this.numberOfReview = numberOfReview;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
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
