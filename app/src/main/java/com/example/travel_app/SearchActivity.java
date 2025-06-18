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
                new Country("Austria", "https://flagcdn.com/w320/at.png"),
                new Country("Belgium", "https://flagcdn.com/w320/be.png"),
                new Country("Bulgaria", "https://flagcdn.com/w320/bg.png"),
                new Country("Croatia", "https://flagcdn.com/w320/hr.png"),
                new Country("Cyprus", "https://flagcdn.com/w320/cy.png"),
                new Country("Czechia", "https://flagcdn.com/w320/cz.png"),
                new Country("Denmark", "https://flagcdn.com/w320/dk.png"),
                new Country("Estonia", "https://flagcdn.com/w320/ee.png"),
                new Country("Finland", "https://flagcdn.com/w320/fi.png"),
                new Country("France", "https://flagcdn.com/w320/fr.png"),
                new Country("Germany", "https://flagcdn.com/w320/de.png"),
                new Country("Greece", "https://flagcdn.com/w320/gr.png"),
                new Country("Hungary", "https://flagcdn.com/w320/hu.png"),
                new Country("Ireland", "https://flagcdn.com/w320/ie.png"),
                new Country("Italy", "https://flagcdn.com/w320/it.png"),
                new Country("Latvia", "https://flagcdn.com/w320/lv.png"),
                new Country("Lithuania", "https://flagcdn.com/w320/lt.png"),
                new Country("Luxembourg", "https://flagcdn.com/w320/lu.png"),
                new Country("Malta", "https://flagcdn.com/w320/mt.png"),
                new Country("Netherlands", "https://flagcdn.com/w320/nl.png"),
                new Country("Poland", "https://flagcdn.com/w320/pl.png"),
                new Country("Portugal", "https://flagcdn.com/w320/pt.png"),
                new Country("Romania", "https://flagcdn.com/w320/ro.png"),
                new Country("Slovakia", "https://flagcdn.com/w320/sk.png"),
                new Country("Slovenia", "https://flagcdn.com/w320/si.png"),
                new Country("Spain", "https://flagcdn.com/w320/es.png"),
                new Country("Sweden", "https://flagcdn.com/w320/se.png")
        );

        // Adapter'ı RecyclerView'e bağlama
        adapter = new CountryAdapter(this, countryList);
        recyclerView.setAdapter(adapter);

        // Ülke tıklama olayını ayarlama
        adapter.setOnItemClickListener((position) -> {
            String selectedCountry = countryList.get(position).getName();
            Intent intent;

            // Ülke adına göre doğru aktiviteye yönlendirme
            switch (selectedCountry) {
                case "Austria":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Belgium":
                    intent = new Intent(SearchActivity.this, BelgiumActivity.class);
                    break;
                case "Bulgaria":
                    intent = new Intent(SearchActivity.this, BulgariaActivity.class);
                    break;
                case "Croatia":
                    intent = new Intent(SearchActivity.this, CroatiaActivity.class);
                    break;
                case "Cyprus":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Czechia":
                    intent = new Intent(SearchActivity.this, CzechiaActivity.class);
                    break;
                case "Denmark":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Estonia":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Finland":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "France":
                    intent = new Intent(SearchActivity.this, FranceActivity.class);
                    break;
                case "Germany":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Greece":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Hungary":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Ireland":
                    intent = new Intent(SearchActivity.this, AustriaActivity.class);
                    break;
                case "Italy":
                    intent = new Intent(SearchActivity.this, ItalyActivity.class);
                    break;


                // Diğer ülkeler için aynı yapı uygulanabilir
                default:
                    intent = new Intent(SearchActivity.this, MainActivity.class);
                    break;
            }
            startActivity(intent);
        });

        // Bottom Navigation Butonlarını Tanımla
        ImageView homeBtn = findViewById(R.id.homeBtn);
        ImageView searchBtn = findViewById(R.id.searchBtn);
        ImageView mapBtn = findViewById(R.id.mapBtn);
        ImageView profileBtn = findViewById(R.id.profileBtn);

        // Buton Renklerini Ayarla
        searchBtn.setColorFilter(getResources().getColor(R.color.dark_green));
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

    @Override
    public void onBackPressed() {
        // Geri tuşuna basıldığında home Sayfasına dön
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}

