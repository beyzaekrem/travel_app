package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        recyclerView = findViewById(R.id.recyclerViewCountries);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

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


        adapter = new CountryAdapter(this, countryList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            String selectedCountry = countryList.get(position).getName();
            Intent intent = new Intent(PhotosActivity.this, CountryPhotoActivity.class);
            intent.putExtra("country", selectedCountry);
            startActivity(intent);
        });
    }
}
