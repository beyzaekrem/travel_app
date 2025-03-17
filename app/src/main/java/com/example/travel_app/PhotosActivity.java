package com.example.travel_app;

import com.example.travel_app.MapActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCities;
    private ImageView homeBtn, searchBtn, mapBtn, profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        recyclerViewCities = findViewById(R.id.recyclerViewCities);
        homeBtn = findViewById(R.id.homeBtn);
        searchBtn = findViewById(R.id.searchBtn);
        mapBtn = findViewById(R.id.mapBtn);
        profileBtn = findViewById(R.id.profileBtn);

        // **Şehir Listesi (Tüm ülkeleri kapsıyor)**
        List<String> cityList = Arrays.asList(
                "Austria", "Belgium", "Bulgaria",
                "Croatia", "Cyprus", "Czechia",
                "Denmark", "Estonia", "Finland",
                "France", "Germany", "Greece",
                "Hungary", "Ireland", "Italy",
                "Latvia", "Lithuania", "Luxembourg",
                "Malta", "Netherlands", "Poland",
                "Portugal", "Romania", "Slovakia",
                "Slovenia", "Spain", "Sweden"
        );

        // **CityAdapter ile bağlama**
        CityAdapter cityAdapter = new CityAdapter(cityList, city -> {
            Intent intent = new Intent(PhotosActivity.this, CityPhotoActivity.class);
            intent.putExtra("city_name", city);
            startActivity(intent);
        });

        recyclerViewCities.setLayoutManager(new GridLayoutManager(this, 3)); // 3 sütunlu düzen
        recyclerViewCities.setAdapter(cityAdapter);

        // **Alt Navigasyon Butonları Çalışsın**
        homeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PhotosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PhotosActivity.this, SearchActivity.class);
            startActivity(intent);
            finish();
        });

        mapBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PhotosActivity.this, MapActivity.class);
            startActivity(intent);
            finish();
        });

        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PhotosActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
