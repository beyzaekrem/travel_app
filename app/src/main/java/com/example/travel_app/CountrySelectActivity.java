package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class CountrySelectActivity extends AppCompatActivity {

    private ListView countryListView;
    private ArrayList<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_select);

        countryListView = findViewById(R.id.countryListView);

        // Görseldeki 27 ülkenin listesini oluştur
        countries = new ArrayList<>(Arrays.asList(
                "Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czechia", "Denmark", "Estonia",
                "Finland", "France", "Germany", "Greece", "Hungary", "Ireland",
                "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands",
                "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain",
                "Sweden"
        ));

        // Liste adaptörünü ayarla
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, countries
        );
        countryListView.setAdapter(adapter);

        // Liste tıklama olayı
        countryListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCountry = countries.get(position);
            Intent intent;

            // Ülke adına göre doğru aktiviteye yönlendirme
            switch (selectedCountry) {
                case "Italy":
                    intent = new Intent(this, ItalyActivity.class);
                    break;

                // Varsayılan olarak bir ana sayfaya yönlendirme yapılabilir
                default:
                    intent = new Intent(this, MainActivity.class);
                    break;
            }

            intent.putExtra("countryName", selectedCountry);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        // Geri tuşuna basıldığında SearchActivity'e dön
        Intent intent = new Intent(CountrySelectActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}

