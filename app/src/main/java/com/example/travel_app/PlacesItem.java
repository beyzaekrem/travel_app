package com.example.travel_app;

public class PlacesItem {
    private String name;
    private String location;
    private int imageResId;
    private String description; // 🆕 Açıklama alanı

    public PlacesItem(String name, String location, int imageResId, String description) {
        this.name = name;
        this.location = location;
        this.imageResId = imageResId;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
