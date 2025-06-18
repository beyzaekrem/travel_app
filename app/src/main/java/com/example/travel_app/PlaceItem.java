package com.example.travel_app;

public class PlaceItem {
    private String name;
    private String imageUrl;
    private String location;

    public PlaceItem(String name, String imageUrl, String location) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLocation() {
        return location;
    }
}
