package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CityPhotoActivity extends AppCompatActivity {

    private TextView cityNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_photo);

        cityNameTextView = findViewById(R.id.cityNameTextView);

        // Şehir adını al ve ekranda göster
        String cityName = getIntent().getStringExtra("city_name");
        cityNameTextView.setText(cityName);
    }
}
