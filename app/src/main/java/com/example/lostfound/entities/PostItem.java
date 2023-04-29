package com.example.lostfound.entities;

import java.util.Date;

public class PostItem {
    private int id;
    private boolean postType;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;

    public PostItem(int id, boolean postType, String name, String phone, String description, String date, String location) {
        this.id = id;
        this.postType = postType;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public boolean isPostType() {
        return postType;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
