package com.example.travel_app;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CountryDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        TextView countryNameText = findViewById(R.id.textViewCountryName);
        String countryName = getIntent().getStringExtra("country_name");
        countryNameText.setText(countryName);
    }
}
