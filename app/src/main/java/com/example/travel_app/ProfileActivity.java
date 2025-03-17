package com.example.travel_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profileImage, homeBtn, searchBtn, mapBtn, profileBtn;
    private TextView profileName, profileEmail;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // **Butonları ve bileşenleri tanımla**
        initializeViews();

        // **Kullanıcı verilerini yükle**
        loadUserData();

        // **Profil ayarlarına git**
        LinearLayout profileSection = findViewById(R.id.profileSection);
        profileSection.setOnClickListener(v -> openActivity(ProfileEditActivity.class));

        // **My Photos Sayfasına Git**
        LinearLayout myPhotosSection = findViewById(R.id.myPhotosSection);
        myPhotosSection.setOnClickListener(v -> openActivity(PhotosActivity.class));

        // **Alt Navigasyon Butonlarını Ayarla**
        setupNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // **Geri dönüldüğünde bilgileri güncelle**
        loadUserData();
    }

    private void initializeViews() {
        homeBtn = findViewById(R.id.homeBtn);
        searchBtn = findViewById(R.id.searchBtn);
        mapBtn = findViewById(R.id.mapBtn);
        profileBtn = findViewById(R.id.profileBtn);
        profileImage = findViewById(R.id.profileImage);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
    }

    private void loadUserData() {
        // **Kayıtlı kullanıcı bilgilerini al**
        String firstName = sharedPreferences.getString("firstName", "").trim();
        String lastName = sharedPreferences.getString("lastName", "").trim();
        String email = sharedPreferences.getString("email", "").trim();
        String selectedAvatar = sharedPreferences.getString("profileAvatar", "");

        // **Eğer bilgiler eksikse varsayılan değer ata**
        if (firstName.isEmpty()) firstName = "Adınız";
        if (lastName.isEmpty()) lastName = "Soyadınız";
        if (email.isEmpty()) email = "email@example.com";

        profileName.setText(firstName + " " + lastName);
        profileEmail.setText(email);

        // **Kayıtlı avatarı yükle veya varsayılan atama yap**
        if (selectedAvatar.isEmpty() || selectedAvatar.equals("female")) {
            profileImage.setImageResource(R.drawable.avatar_female);
        } else {
            profileImage.setImageResource(R.drawable.avatar_male);
        }
    }

    private void setupNavigation() {
        homeBtn.setOnClickListener(v -> openActivity(MainActivity.class));
        searchBtn.setOnClickListener(v -> openActivity(SearchActivity.class));
        mapBtn.setOnClickListener(v -> openActivity(MapActivity.class)); // **📍 Harita Sekmesi Aktif**
        profileBtn.setOnClickListener(v -> openActivity(ProfileActivity.class));

        // **Aktif butonu vurgula**
        highlightActiveButton();
    }

    private void highlightActiveButton() {
        profileBtn.setColorFilter(getResources().getColor(R.color.dark_green));
        homeBtn.setColorFilter(getResources().getColor(android.R.color.white));
        searchBtn.setColorFilter(getResources().getColor(android.R.color.white));
        mapBtn.setColorFilter(getResources().getColor(android.R.color.white));
    }

    private void openActivity(Class<?> destination) {
        // **Eğer zaten aynı sayfada değilsek yönlendir**
        if (!this.getClass().equals(destination)) {
            Intent intent = new Intent(ProfileActivity.this, destination);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish(); // Önceki aktiviteyi kapat
        }
    }
}
