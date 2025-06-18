package com.example.travel_app;

public class HotelItem {
    private String name;
    private String rating;
    private String location;
    private int imageResId;

    public HotelItem(String name, String rating, String location, int imageResId) {
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getRating() { return rating; }
    public String getLocation() { return location; }
    public int getImageResId() { return imageResId; }
}
