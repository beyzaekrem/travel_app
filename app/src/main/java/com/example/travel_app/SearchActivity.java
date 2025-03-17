package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;
import android.widget.ImageView;


public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerViewCountries);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 sütunlu görünüm

        // Ülke Listesi
        countryList = Arrays.asList(
                new Country("Austria"), new Country("Belgium"), new Country("Bulgaria"),
                new Country("Croatia"), new Country("Cyprus"), new Country("Czechia"),
                new Country("Denmark"), new Country("Estonia"), new Country("Finland"),
                new Country("France"), new Country("Germany"), new Country("Greece"),
                new Country("Hungary"), new Country("Ireland"), new Country("Italy"),
                new Country("Latvia"), new Country("Lithuania"), new Country("Luxembourg"),
                new Country("Malta"), new Country("Netherlands"), new Country("Poland"),
                new Country("Portugal"), new Country("Romania"), new Country("Slovakia"),
                new Country("Slovenia"), new Country("Spain"), new Country("Sweden")
        );


        // Adapter'ı RecyclerView'e bağlama
        adapter = new CountryAdapter(this, countryList);
        recyclerView.setAdapter(adapter);
        // Bottom Navigation Butonlarını Tanımla
        ImageView homeBtn = findViewById(R.id.homeBtn);
        ImageView searchBtn = findViewById(R.id.searchBtn);
        ImageView mapBtn = findViewById(R.id.mapBtn);
        ImageView profileBtn = findViewById(R.id.profileBtn);

// Buton Renklerini Ayarla
        searchBtn.setColorFilter(getResources().getColor(R.color.dark_green)); // Search Butonu Koyu Yeşil
        homeBtn.setColorFilter(getResources().getColor(R.color.white));
        mapBtn.setColorFilter(getResources().getColor(R.color.white));
        profileBtn.setColorFilter(getResources().getColor(R.color.white));

// Ana Sayfa Butonu - MainActivity'e Git
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, MainActivity.class);
            startActivity(intent);
        });

// Harita Butonu - MapActivity'e Git
        mapBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, MapActivity.class);
            startActivity(intent);
        });

// Profil Butonu - ProfileActivity'e Git
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

    }
}
