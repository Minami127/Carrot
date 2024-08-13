package com.example.carrotapp.model;

import java.io.Serializable;
import java.util.List;

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
    private String productImageUrl;
    private String createdAt;
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



