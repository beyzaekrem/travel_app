package com.example.travel_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import java.io.IOException;
import com.caverock.androidsvg.SVGParseException;

public class TravelMapActivity extends AppCompatActivity {

    private SVGImageView svgImageView;
    private Button colorBtn;
    private SVG svg;
    private static final String TAG = "TravelMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_map);

        svgImageView = findViewById(R.id.svgImageView);
        colorBtn = findViewById(R.id.colorBtn);

        loadMap();

        // Ülke seçme butonuna tıklayınca seçim ekranına geçiş
        colorBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TravelMapActivity.this, CountrySelectActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Ülke seçme ekranına geçildi!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadMap() {
        try {
            // SVG haritasını yükle
            svg = SVG.getFromAsset(getAssets(), "europe_map.svg");
            svgImageView.setSVG(svg);
            Toast.makeText(this, "Harita yüklendi!", Toast.LENGTH_SHORT).show();
        } catch (SVGParseException | IOException e) {
            Toast.makeText(this, "Harita yüklenemedi!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String detectClickedCountry() {
        // Geçici olarak "france" döndürüyoruz (dinamik hale getireceğiz)
        return "france";
    }

    private void colorCountry(String countryId) {
        try {
            // SVG içeriği üzerinden doğru ID'yi bulmaya çalış
            String svgContent = svg.toString();
            String searchPattern = "id=\"" + countryId.toLowerCase() + "\"";
            String replacement = "fill=\"#FF0000\""; // Kırmızı renk örneği

            if (svgContent.contains(searchPattern)) {
                // Alt öğelerdeki fill değerini değiştirme (path veya polygon)
                String regex = searchPattern + "[\\s\\S]*?(<path|<polygon)[^>]*fill=\"#([0-9A-Fa-f]{6})\"";
                String updatedSvg = svgContent.replaceAll(regex, "$1 fill=\"#FF0000\"");

                svg = SVG.getFromString(updatedSvg);
                svgImageView.setSVG(svg);
                svgImageView.invalidate();
                Toast.makeText(this, countryId + " renklendirildi!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ülke bulunamadı: " + countryId, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Ülke bulunamadı: " + countryId);
            }
        } catch (SVGParseException e) {
            Toast.makeText(this, "Hata: SVG işlenemedi!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        // Geri tuşuna basıldığında Profil Sayfasına dön
        Intent intent = new Intent(TravelMapActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();  // Yönlendirmeden sonra çağırmak, bazı cihazlarda uyumluluğu artırabilir
    }
}
