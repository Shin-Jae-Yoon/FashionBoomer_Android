package com.example.fashionboomer.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ClothBean {
    public static class ResponseCloth {
        @SerializedName("data")
        private List<Cloth> data;

        public ResponseCloth(ResponseCloth responseCloth) {
            this.data = new ArrayList<>();
            for (Cloth cloth : responseCloth.getData()) {
                this.data.add(new Cloth(cloth.id, cloth.category, cloth.details, cloth.gender, cloth.name, cloth.brand, cloth.link, cloth.price));
            }
        }

        public List<Cloth> getData() {
            return data;
        }

        public void setData(List<Cloth> data) {
            this.data = data;
        }
    }

    public static class Cloth {
        @SerializedName("id")
        private int id;

        @SerializedName("category")
        private String category;

        @SerializedName("details")
        private String details;

        @SerializedName("gender")
        private String gender;

        @SerializedName("name")
        private String name;

        @SerializedName("brand")
        private String brand;

        @SerializedName("price")
        private String price;

        @SerializedName("link")
        private String link;

        public int getId() {
            return id;
        }

        public String getCategory() {
            return category;
        }

        public String getDetails() {
            return details;
        }

        public String getGender() {
            return gender;
        }

        public String getName() {
            return name;
        }

        public String getBrand() {
            return brand;
        }

        public String getPrice() {
            return price;
        }

        public String getLink() {
            return link;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Cloth(int id,String category, String details, String gender, String name, String brand, String link, String price) {
            this.id = id;
            this.category = category;
            this.details = details;
            this.gender = gender;
            this.name = name;
            this.brand = brand;
            this.link = link;
            this.price = price;
        }
    }

}
