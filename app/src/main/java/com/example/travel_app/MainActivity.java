package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView homeBtn, searchBtn, mapBtn, profileBtn;
    private CardView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // **Sistem çubukları için padding ayarla**
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // **Alt Navigasyon Çubuğunu Tanımla**
        initializeViews();

        // **Alt Navigasyon Butonlarını Tanımla**
        setupNavigation();
    }

    private void initializeViews() {
        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setCardBackgroundColor(getResources().getColor(R.color.green));

        homeBtn = findViewById(R.id.homeBtn);
        searchBtn = findViewById(R.id.searchBtn);
        mapBtn = findViewById(R.id.mapBtn);
        profileBtn = findViewById(R.id.profileBtn);
    }

    private void setupNavigation() {
        homeBtn.setOnClickListener(v -> openActivity(MainActivity.class, homeBtn));
        searchBtn.setOnClickListener(v -> openActivity(SearchActivity.class, searchBtn));
        mapBtn.setOnClickListener(v -> openActivity(MapActivity.class, mapBtn));
        profileBtn.setOnClickListener(v -> openActivity(ProfileActivity.class, profileBtn));

        // **Ana sayfa aktif olduğu için Home butonunu yeşil yap**
        setActiveButton(homeBtn);
    }

    /**
     * Yeni aktiviteyi açar ve aynı sayfaya geçmeye çalışmasını engeller.
     */
    private void openActivity(Class<?> destination, ImageView activeButton) {
        if (!this.getClass().equals(destination)) {
            Intent intent = new Intent(MainActivity.this, destination);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        setActiveButton(activeButton);
    }

    /**
     * Butonların rengini sıfırlar ve yalnızca aktif butonu yeşil yapar.
     */
    private void setActiveButton(ImageView activeButton) {
        homeBtn.setColorFilter(getResources().getColor(R.color.inactive_button));
        searchBtn.setColorFilter(getResources().getColor(R.color.inactive_button));
        mapBtn.setColorFilter(getResources().getColor(R.color.inactive_button));
        profileBtn.setColorFilter(getResources().getColor(R.color.inactive_button));

        // **Aktif butonu yeşil yap**
        activeButton.setColorFilter(getResources().getColor(R.color.active_button));
    }
}
