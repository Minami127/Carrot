package com.example.carrotapp.model;

import androidx.dynamicanimation.animation.SpringAnimation;

import java.io.Serializable;
import java.util.ArrayList;

public class PostDetail implements Serializable {

    public String result;

    public ArrayList<Item> items;

    public class Item {
        private int id;
        private int seller_id;
        private int category_id;
        private String title;
        private int price;
        private String description;
        private int product_state;
        private int viewCnt;
        private String product_image_url;
        private String created_at;
        private String updated_at;
        private String location;
        private String nickname;
        private String profile_img;

        // getters and setters
        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getProductImageUrl() {
            return product_image_url;
        }
        public int getPrice() {
            return price;
        }

        public String getNickname(){
            return nickname;
        }

        public String getProfile_img(){
            return profile_img;
        }

    }
}
