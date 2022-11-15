package com.example.fashionboomer.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    public static class ResponseMember {
        @SerializedName("data")
        private Member responseMember;

        public Member getResponseMember() {
            return responseMember;
        }

        public void setResponseMember(Member responseMember) {
            this.responseMember = responseMember;
        }
    }

    public static class Member {
        @SerializedName("memberId")
        private Long memberId;

        @SerializedName("email")
        private String email;

        @SerializedName("name")
        private String name;

        @SerializedName("platform")
        private String platform;

        public Long getMemberId() {
            return memberId;
        }

        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getPlatform() {
            return platform;
        }

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public Member(String email, String name, String platform) {
            this.email = email;
            this.name = name;
            this.platform = platform;
        }
    }


    public static class PageData {
        @SerializedName("data")
        private List<Closet> data;

        @SerializedName("pageInfo")
        private PageInfo pageInfo;

        public List<Closet> getData() {
            return data;
        }

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setData(List<Closet> data) {
            this.data = data;
        }

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }
    }

    public static class Data {
        @SerializedName("data")
        private Closet data;

        public Closet getData() {
            return data;
        }

        public void setData(Closet data) {
            this.data = data;
        }
    }

    public static class Closet {
        @SerializedName("id")
        private int id;

        @SerializedName("user_id")
        private Long user_id;

        @SerializedName("cloth_id")
        private int cloth_id;

        public int getId() {
            return id;
        }

        public Long getUser_id() {
            return user_id;
        }

        public int getCloth_id() {
            return cloth_id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUser_id(Long user_id) {
            this.user_id = user_id;
        }

        public void setCloth_id(int cloth_id) {
            this.cloth_id = cloth_id;
        }
    }

    public static class PageInfo {
        @SerializedName("page")
        private int page;

        @SerializedName("size")
        private int size;

        @SerializedName("totalElements")
        private int totalElements;

        @SerializedName("totalPages")
        private int totalPages;

        public int getPage() {
            return page;
        }

        public int getSize() {
            return size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

}