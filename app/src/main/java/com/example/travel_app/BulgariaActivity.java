package com.example.travel_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BulgariaActivity extends AppCompatActivity {

    private Button btnHotels, btnFoods, btnPlaces;
    private TextView tvCountryDescription;
    private boolean isFavorite = false;
    private final String countryName = "Bulgaria";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulgaria);

        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        btnHotels = findViewById(R.id.btnHotels);
        btnFoods = findViewById(R.id.btnFoods);
        btnPlaces = findViewById(R.id.btnPlaces);
        tvCountryDescription = findViewById(R.id.tvCountryDescription);

        // Açıklama metni
        tvCountryDescription.setText(
                "🇧🇬 \nBulgaria is a land of rich history, diverse landscapes, and vibrant traditions. " +
                        "From the golden beaches of the Black Sea to the snow-capped Pirin Mountains, " +
                        "and from ancient Thracian ruins to colorful folklore festivals, Bulgaria is a hidden gem in Eastern Europe."
        );

        updateButtonStyles(btnHotels);

        btnHotels.setOnClickListener(v -> {
            updateButtonStyles(btnHotels);
            startActivity(new Intent(this, HotelsActivity.class).putExtra("country", countryName));
        });

        btnFoods.setOnClickListener(v -> {
            updateButtonStyles(btnFoods);
            startActivity(new Intent(this, FoodActivity.class).putExtra("country", countryName));
        });

        btnPlaces.setOnClickListener(v -> {
            updateButtonStyles(btnPlaces);
            startActivity(new Intent(this, PlacesActivity.class).putExtra("country", countryName));
        });
    }

    private void updateButtonStyles(Button activeButton) {
        btnHotels.setBackgroundColor(getResources().getColor(R.color.inactive_button));
        btnFoods.setBackgroundColor(getResources().getColor(R.color.inactive_button));
        btnPlaces.setBackgroundColor(getResources().getColor(R.color.inactive_button));
        activeButton.setBackgroundColor(getResources().getColor(R.color.active_button));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_menu, menu);

        MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
        isFavorite = getSharedPreferences("favorites", MODE_PRIVATE).getBoolean(countryName, false);
        updateFavoriteIcon(favoriteItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_favorite) {
            isFavorite = !isFavorite;
            getSharedPreferences("favorites", MODE_PRIVATE)
                    .edit()
                    .putBoolean(countryName, isFavorite)
                    .apply();

            updateFavoriteIcon(item);
            String msg = isFavorite ? "Added to favorites" : "Removed from favorites";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_share) {
            shareCountryInfo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateFavoriteIcon(MenuItem item) {
        item.setIcon(isFavorite ? R.drawable.ic_heart_filled : R.drawable.ic_heart_outline);
    }

    private void shareCountryInfo() {
        String shareText = "Bulgaristan'daki favori yerlerimi keşfet! 🇧🇬\n" +
                "Oteller, yemekler ve gezilecek yerler burada:\n" +
                "mytravelapp://country/Bulgaria";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Paylaş:");
        startActivity(shareIntent);
    }
}
