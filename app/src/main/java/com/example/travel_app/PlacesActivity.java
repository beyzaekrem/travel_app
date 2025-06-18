package com.example.travel_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity {

    private TextView countryName;
    private ImageView countryImage;
    private RecyclerView placesRecyclerView;
    private PlacesAdapter placesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        countryName = findViewById(R.id.countryName);
        countryImage = findViewById(R.id.countryImage);
        placesRecyclerView = findViewById(R.id.placesRecyclerView);

        String country = getIntent().getStringExtra("country");
        if (country == null) {
            country = "Italy";
        }

        countryName.setText(country);
        countryImage.setImageResource(getCountryImage(country));

        ArrayList<PlacesItem> placeList = getPlaceList(country);

        // 🔄 Güncellenmiş adapter çağrısı
        placesAdapter = new PlacesAdapter(this, placeList);

        placesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placesRecyclerView.setAdapter(placesAdapter);
    }

    // Ülke adına göre görsel döndür
    private int getCountryImage(String country) {
        switch (country) {

            case "Austria":
                return R.drawable.austria;
            case "Belgium":
                return R.drawable.belgium;
            case "Bulgaria":
                return R.drawable.bulgaria;
            case "Croatia":
                return R.drawable.croatia;
            case "France":
                return R.drawable.france;
            default:
                return R.drawable.italy;
        }
    }

    // Ülke adına göre gezilecek yer listesi
    private ArrayList<PlacesItem> getPlaceList(String country) {
        ArrayList<PlacesItem> placeList = new ArrayList<>();

        switch (country) {



            case "Austria":
                placeList.add(new PlacesItem("Schönbrunn Palace", "Vienna", R.drawable.schonbrunn, "Habsburg hanedanının yazlık sarayı, barok mimarinin örneğidir."));
                placeList.add(new PlacesItem("Hofburg Palace", "Vienna", R.drawable.hofburg, "Avusturya İmparatorluğu'nun yönetim merkezi olmuştur."));
                placeList.add(new PlacesItem("Hallstatt Village", "Hallstatt", R.drawable.hallstatt, "Göl kenarında yer alan kartpostallık tarihi köy."));
                break;

            case "Belgium":
                placeList.add(new PlacesItem("Grand Place", "Brussels", R.drawable.grand_place, "Brüksel'in tarihi merkezi, gotik ve barok binalarla çevrili."));
                placeList.add(new PlacesItem("Atomium", "Brussels", R.drawable.atomium, "1958 Expo için yapılmış, demir kristal yapısını temsil eden anıt."));
                placeList.add(new PlacesItem("Bruges Old Town", "Bruges", R.drawable.bruges, "Orta Çağ'dan kalma, kanalları ve taş sokaklarıyla ünlü şehir."));
                break;

            case "Bulgaria":
                placeList.add(new PlacesItem("Alexander Nevsky Cathedral", "Sofia", R.drawable.bulgariaplace1, "Sofya'nın simgesi olan bu katedral, Bulgar Ortodoks mirasının baş tacıdır."));
                placeList.add(new PlacesItem("Rila Monastery", "Rila Mountains", R.drawable.bulgariaplace2, "UNESCO korumasındaki bu manastır, Bulgaristan’ın ruhani ve tarihi merkezidir."));
                placeList.add(new PlacesItem("Plovdiv Old Town", "Plovdiv", R.drawable.bulgariaplace3, "Roma amfitiyatrosu ve renkli Osmanlı evleriyle dolu tarihi şehir."));
                break;


            case "Croatia":
                placeList.add(new PlacesItem("Dubrovnik Old Town", "Dubrovnik", R.drawable.dubrovnik, "Surlarla çevrili, Game of Thrones dizisine ev sahipliği yapmış tarihi şehir."));
                placeList.add(new PlacesItem("Plitvice Lakes", "Plitvice", R.drawable.plitvice, "Şelaleleri ve yürüyüş yollarıyla ünlü doğal milli park."));
                placeList.add(new PlacesItem("Diocletian's Palace", "Split", R.drawable.diocletian, "Roma İmparatoru Diocletian için inşa edilmiş tarihi saray kalıntıları."));
                break;

            case "France":
                placeList.add(new PlacesItem("Eiffel Tower", "Paris", R.drawable.eiffel, "1889'da inşa edilen Paris'in simgesi olan demir kule."));
                placeList.add(new PlacesItem("Louvre Museum", "Paris", R.drawable.louvre, "Dünyanın en büyük sanat müzesi, Mona Lisa burada sergilenir."));
                placeList.add(new PlacesItem("Mont Saint-Michel", "Normandy", R.drawable.mont_saint_michel, "Gelgitlerle çevrilen ada ve manastır kompleksi."));
                break;

            case "Italy":
                placeList.add(new PlacesItem("Colosseum", "Rome", R.drawable.colosseum, "MS 80 yılında açılmış, Roma'nın simgelerinden biridir."));
                placeList.add(new PlacesItem("Trevi Fountain", "Rome", R.drawable.trevi, "Aşk çeşmesi olarak bilinen, dilek atılan ünlü barok çeşme."));
                placeList.add(new PlacesItem("Pantheon", "Rome", R.drawable.pantheon, "Tarihi bir Roma tapınağı, günümüzde kilise olarak kullanılmaktadır."));
                break;
            default:
                placeList.add(new PlacesItem("Famous Landmark", "Unknown", R.drawable.default_image, "Genel bir simgesel yer."));
                break;
        }


        return placeList;
    }
}
