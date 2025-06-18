package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CzechiaActivity extends AppCompatActivity {

    private Button btnHotels, btnFoods, btnPlaces;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czechia);

        // Bileşenleri bağla
        btnHotels = findViewById(R.id.btnHotels);
        btnFoods = findViewById(R.id.btnFoods);
        btnPlaces = findViewById(R.id.btnPlaces);
        tvContent = findViewById(R.id.tvContent);

        // Varsayılan olarak "Hotels" butonunu aktif yap
        updateButtonStyles(btnHotels);
        tvContent.setText("Showing Hotels...");

        // Hotels butonuna tıklama işlemi
        btnHotels.setOnClickListener(v -> {
            updateButtonStyles(btnHotels);
            tvContent.setText("Showing Hotels...");
            Toast.makeText(this, "Hotels Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CzechiaActivity.this, HotelsActivity.class);
            intent.putExtra("country", "Czechia");  // Ülke adını gönderiyoruz
            startActivity(intent);
        });

        // Foods butonuna tıklama işlemi
        btnFoods.setOnClickListener(v -> {
            updateButtonStyles(btnFoods);
            tvContent.setText("Showing Foods...");
            Toast.makeText(this, "Foods Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CzechiaActivity.this, FoodActivity.class);
            intent.putExtra("country", "Czechia");  // Ülke adını gönderiyoruz
            startActivity(intent);
        });

        // Places butonuna tıklama işlemi
        btnPlaces.setOnClickListener(v -> {
            updateButtonStyles(btnPlaces);
            tvContent.setText("Showing Places...");
            Toast.makeText(this, "Places Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CzechiaActivity.this, PlacesActivity.class);
            intent.putExtra("country", "Czechia");  // Ülke adını gönderiyoruz
            startActivity(intent);
        });
    }

    // Buton arka plan renklerini güncelleme fonksiyonu
    private void updateButtonStyles(Button activeButton) {
        // Tüm butonları varsayılan yap
        btnHotels.setBackgroundColor(getResources().getColor(R.color.inactive_button));
        btnFoods.setBackgroundColor(getResources().getColor(R.color.inactive_button));
        btnPlaces.setBackgroundColor(getResources().getColor(R.color.inactive_button));

        // Tıklanan butonu aktif yap
        activeButton.setBackgroundColor(getResources().getColor(R.color.active_button));
    }
}
