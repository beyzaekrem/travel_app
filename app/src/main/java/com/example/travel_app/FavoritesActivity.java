package com.example.travel_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private List<FavoriteItem> favoriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteList = new ArrayList<>();
        adapter = new FavoritesAdapter(this, favoriteList);
        recyclerView.setAdapter(adapter);

        loadFavorites();

        // 🔍 SearchView ile filtreleme
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // Klavyede "ara" tuşuna basıldığında bir şey yapma
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    private void loadFavorites() {
        SharedPreferences prefs = getSharedPreferences("favorites", MODE_PRIVATE);
        List<FavoriteItem> updatedList = new ArrayList<>();

        if (prefs.getBoolean("Austria", false)) {
            updatedList.add(new FavoriteItem("Austria", "https://flagcdn.com/w320/at.png"));
        }
        if (prefs.getBoolean("Belgium", false)) {
            updatedList.add(new FavoriteItem("Belgium", "https://flagcdn.com/w320/be.png"));
        }
        if (prefs.getBoolean("Bulgaria", false)) {
            updatedList.add(new FavoriteItem("Bulgaria", "https://flagcdn.com/w320/bg.png"));
        }
        if (prefs.getBoolean("Croatia", false)) {
            updatedList.add(new FavoriteItem("Croatia", "https://flagcdn.com/w320/hr.png"));
        }
        if (prefs.getBoolean("Cyprus", false)) {
            updatedList.add(new FavoriteItem("Cyprus", "https://flagcdn.com/w320/cy.png"));
        }
        if (prefs.getBoolean("Czechia", false)) {
            updatedList.add(new FavoriteItem("Czechia", "https://flagcdn.com/w320/cz.png"));
        }
        if (prefs.getBoolean("Denmark", false)) {
            updatedList.add(new FavoriteItem("Denmark", "https://flagcdn.com/w320/dk.png"));
        }
        if (prefs.getBoolean("Estonia", false)) {
            updatedList.add(new FavoriteItem("Estonia", "https://flagcdn.com/w320/ee.png"));
        }
        if (prefs.getBoolean("Finland", false)) {
            updatedList.add(new FavoriteItem("Finland", "https://flagcdn.com/w320/fi.png"));
        }
        if (prefs.getBoolean("France", false)) {
            updatedList.add(new FavoriteItem("France", "https://flagcdn.com/w320/fr.png"));
        }
        if (prefs.getBoolean("Italy", false)) {
            updatedList.add(new FavoriteItem("Italy", "https://flagcdn.com/w320/it.png"));
        }


        adapter.updateList(updatedList); // 🔥 Burada yeni verilerle adapter'ı güncelliyoruz
    }

}
