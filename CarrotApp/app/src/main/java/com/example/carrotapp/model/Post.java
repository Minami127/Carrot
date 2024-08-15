package com.example.carrotapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Post implements Serializable {
    private int id;
    private int sellerId;
    private int categoryId;
    private String title;
    private int price;
    private String description;
    private String location;
    private int productState;
    private int viewCnt;
    @SerializedName("product_image_url")
    private String productImageUrl;

    @SerializedName("created_at")
    private String createdAt;


    @SerializedName("updated_at")
    private String updatedAt;


    // Getters
    public Post(int id, int sellerId,int categoryId, String productImageUrl, String title,int price, String description,int productState, String createdAt, int viewCnt,String updatedAt,String location) {
        this.id = id;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.title = title;
        this.price = price;
        this.productImageUrl = productImageUrl;
        this.productState = productState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
        this.viewCnt = viewCnt;
        this.location = location;
    }
    // Getters
    public int getId() {
        return id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getProductState() {
        return productState;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getLocation() {
        return location;
    }


}



