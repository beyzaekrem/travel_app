package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentContainerView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView homeBtn, searchBtn, mapBtn, profileBtn;
    private CardView bottomNav;
    private FragmentContainerView mapContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Harita fragmentını başlat
        mapContainer = findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Harita yüklenemedi!", Toast.LENGTH_SHORT).show();
        }

        // Navigasyon Çubuğunu Tanımla
        initializeViews();
        setupNavigation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng europeCenter = new LatLng(50.1000, 14.4000); // Avrupa'nın merkezi (yaklaşık Prag)
        mMap.addMarker(new MarkerOptions().position(europeCenter).title("Avrupa Merkezi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(europeCenter, 5.0f));



        // Kullanıcı konumunu gösterme (izin verilmişse)
        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            Toast.makeText(this, "Konum izni verilmedi!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeViews() {
        bottomNav = findViewById(R.id.bottomNav);
        homeBtn = findViewById(R.id.homeBtn);
        searchBtn = findViewById(R.id.searchBtn);
        mapBtn = findViewById(R.id.mapBtn);
        profileBtn = findViewById(R.id.profileBtn);
    }

    private void setupNavigation() {
        homeBtn.setOnClickListener(v -> openActivity(MainActivity.class));
        searchBtn.setOnClickListener(v -> openActivity(SearchActivity.class));
        mapBtn.setOnClickListener(v -> openActivity(MapActivity.class));
        profileBtn.setOnClickListener(v -> openActivity(ProfileActivity.class));
    }

    private void openActivity(Class<?> destination) {
        if (!this.getClass().equals(destination)) {
            Intent intent = new Intent(MapActivity.this, destination);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
    public void onBackPressed() {
        // Geri tuşuna basıldığında home Sayfasına dön
        Intent intent = new Intent(MapActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();  // Yönlendirmeden sonra çağırmak, bazı cihazlarda uyumluluğu artırabilir
    }
}
