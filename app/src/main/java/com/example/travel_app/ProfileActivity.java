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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        loadUserData();

        // Alt bölümler
        findViewById(R.id.profileSection).setOnClickListener(v -> openActivity(ProfileEditActivity.class));
        findViewById(R.id.favoritesSection).setOnClickListener(v -> openActivity(FavoritesActivity.class));
        findViewById(R.id.myPhotosSection).setOnClickListener(v -> openActivity(PhotosActivity.class));
        findViewById(R.id.travelMapSection).setOnClickListener(v -> openActivity(TravelMapActivity.class));

        setupNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
    }

    private void loadUserData() {
        // Aktif kullanıcıyı öğren
        SharedPreferences loginPrefs = getSharedPreferences("activeUser", Context.MODE_PRIVATE);
        String activeEmail = loginPrefs.getString("email", null);

        if (activeEmail != null) {
            SharedPreferences userPrefs = getSharedPreferences(activeEmail, Context.MODE_PRIVATE);

            String firstName = userPrefs.getString("firstName", "").trim();
            String lastName = userPrefs.getString("lastName", "").trim();
            String selectedAvatar = userPrefs.getString("profileAvatar", "female");

            profileName.setText((firstName.isEmpty() ? "Adınız" : firstName) + " " +
                    (lastName.isEmpty() ? "Soyadınız" : lastName));
            profileEmail.setText(activeEmail);

            if (selectedAvatar.equals("male")) {
                profileImage.setImageResource(R.drawable.avatar_male);
            } else {
                profileImage.setImageResource(R.drawable.avatar_female);
            }
        } else {
            profileName.setText("Bilinmeyen Kullanıcı");
            profileEmail.setText("email@example.com");
            profileImage.setImageResource(R.drawable.avatar_female);
        }
    }

    private void setupNavigation() {
        homeBtn.setOnClickListener(v -> openActivity(MainActivity.class));
        searchBtn.setOnClickListener(v -> openActivity(SearchActivity.class));
        mapBtn.setOnClickListener(v -> openActivity(MapActivity.class));
        profileBtn.setOnClickListener(v -> openActivity(ProfileActivity.class));
        highlightActiveButton();
    }

    private void highlightActiveButton() {
        profileBtn.setColorFilter(getResources().getColor(R.color.dark_green));
        homeBtn.setColorFilter(getResources().getColor(android.R.color.white));
        searchBtn.setColorFilter(getResources().getColor(android.R.color.white));
        mapBtn.setColorFilter(getResources().getColor(android.R.color.white));
    }

    private void openActivity(Class<?> destination) {
        if (!this.getClass().equals(destination)) {
            Intent intent = new Intent(ProfileActivity.this, destination);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
