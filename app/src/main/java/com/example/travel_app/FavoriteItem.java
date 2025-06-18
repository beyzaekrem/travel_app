package com.example.travel_app;

public class FavoriteItem {
    private String countryName;
    private String flagUrl;

    public FavoriteItem(String countryName, String flagUrl) {
        this.countryName = countryName;
        this.flagUrl = flagUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getFlagUrl() {
        return flagUrl;
    }
}
