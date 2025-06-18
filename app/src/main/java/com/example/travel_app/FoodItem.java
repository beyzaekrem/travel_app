package com.example.travel_app;

import java.util.List;

public class FoodItem {
    private String name;
    private int imageResource;
    private String[] restaurants;
    private String description;


    public FoodItem(String name, int imageResource, String[] restaurants, String description) {
        this.name = name;
        this.imageResource = imageResource;
        this.restaurants = restaurants;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String[] getRestaurants() {
        return restaurants;
    }
    public String getDescription() {
        return description;
    }

}
