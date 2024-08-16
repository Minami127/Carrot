package com.example.carrotapp.model;

import java.util.ArrayList;

public class PostDetail {

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

        // 추가적인 getter 메서드들...
    }




}
