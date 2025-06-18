package com.example.travel_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    private TextView countryName;
    private ImageView countryImage;
    private RecyclerView hotelRecyclerView;
    private HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        countryName = findViewById(R.id.countryName);
        countryImage = findViewById(R.id.countryImage);
        hotelRecyclerView = findViewById(R.id.hotelRecyclerView);

        String country = getIntent().getStringExtra("country");
        if (country == null) {
            country = "Italy";
        }
        countryName.setText(country);

        // Ülke görselini ayarla
        switch (country) {

            case "Austria":
                countryImage.setImageResource(R.drawable.austria);
                break;
            case "Belgium":
                countryImage.setImageResource(R.drawable.belgium);
                break;
            case "Bulgaria":
                countryImage.setImageResource(R.drawable.bulgaria);
                break;
            case "Croatia":
                countryImage.setImageResource(R.drawable.croatia);
                break;
            case "France":
                countryImage.setImageResource(R.drawable.france);
                break;
            case "Italy":
                countryImage.setImageResource(R.drawable.italy);
                break;

                default:
                countryImage.setImageResource(R.drawable.default_image);
                break;
        }

        ArrayList<HotelItem> hotelList = new ArrayList<>();

        // Ülkeye özel otel verilerini ekle
        switch (country) {
            case "Italy":
                hotelList.add(new HotelItem("Hotel Colosseum", "4.8", "Rome", R.drawable.italyotel1));
                hotelList.add(new HotelItem("Venice Grand Hotel", "4.6", "Venice", R.drawable.italyotel2));
                hotelList.add(new HotelItem("Pantheon Suites", "4.7", "Rome", R.drawable.italyotel3));
                break;

            case "Austria":
                hotelList.add(new HotelItem("Hotel Sacher Wien", "4.9", "Vienna", R.drawable.austriaotel1));
                hotelList.add(new HotelItem("Grand Hotel Wien", "4.7", "Vienna", R.drawable.austriaotel2));
                hotelList.add(new HotelItem("Hotel Goldener Hirsch", "4.6", "Salzburg", R.drawable.austriaotel3));
                break;

            case "Belgium":
                countryImage.setImageResource(R.drawable.belgium); // Belçika bayrağı veya görseli
                hotelList.add(new HotelItem("Hotel Amigo", "4.8", "Brussels", R.drawable.belgiumotel1));
                hotelList.add(new HotelItem("Warwick Brussels", "4.6", "Brussels", R.drawable.belgiumotel2));
                hotelList.add(new HotelItem("Ghent River Hotel", "4.7", "Ghent", R.drawable.belgiumotel3));
                break;

            case "Bulgaria":
                hotelList.add(new HotelItem("Grand Hotel Sofia", "4.7", "Sofia", R.drawable.bulgariaotel1));
                hotelList.add(new HotelItem("Rosslyn Dimyat Hotel", "4.5", "Varna", R.drawable.bulgariaotel2));
                hotelList.add(new HotelItem("Kempinski Hotel Grand Arena", "4.8", "Bansko", R.drawable.bulgariaotel3));
                break;

            case "Croatia":
                hotelList.add(new HotelItem("Hotel Excelsior", "4.9", "Dubrovnik", R.drawable.default_image));
                hotelList.add(new HotelItem("Esplanade Zagreb Hotel", "4.7", "Zagreb", R.drawable.default_image));
                hotelList.add(new HotelItem("Hotel Adriana", "4.6", "Hvar", R.drawable.default_image));
                break;

            case "Cyprus":
                hotelList.add(new HotelItem("Elysium Hotel", "4.8", "Paphos", R.drawable.default_image));
                hotelList.add(new HotelItem("Amara Hotel", "4.7", "Limassol", R.drawable.default_image));
                hotelList.add(new HotelItem("Capo Bay Hotel", "4.6", "Protaras", R.drawable.default_image));
                break;

            case "Czechia":
                hotelList.add(new HotelItem("Hotel Kings Court", "4.8", "Prague", R.drawable.default_image));
                hotelList.add(new HotelItem("NH Collection Prague", "4.6", "Prague", R.drawable.default_image));
                hotelList.add(new HotelItem("Hotel Savoy", "4.7", "Prague", R.drawable.default_image));
                break;

            case "Denmark":
                hotelList.add(new HotelItem("Hotel D'Angleterre", "4.9", "Copenhagen", R.drawable.default_image));
                hotelList.add(new HotelItem("Nimb Hotel", "4.7", "Copenhagen", R.drawable.default_image));
                hotelList.add(new HotelItem("Hotel SP34", "4.6", "Copenhagen", R.drawable.default_image));
                break;

            case "Estonia":
                hotelList.add(new HotelItem("Swissôtel Tallinn", "4.8", "Tallinn", R.drawable.default_image));
                hotelList.add(new HotelItem("Hotel Telegraaf", "4.7", "Tallinn", R.drawable.default_image));
                hotelList.add(new HotelItem("Radisson Blu Sky Hotel", "4.6", "Tallinn", R.drawable.default_image));
                break;

            case "Finland":
                hotelList.add(new HotelItem("Hotel Kämp", "4.8", "Helsinki", R.drawable.default_image));
                hotelList.add(new HotelItem("Clarion Hotel Helsinki", "4.6", "Helsinki", R.drawable.default_image));
                hotelList.add(new HotelItem("Lapland Hotels Bulevardi", "4.7", "Helsinki", R.drawable.default_image));
                break;

            case "France":
                hotelList.add(new HotelItem("Shangri-La Paris", "4.9", "Paris", R.drawable.default_image));
                hotelList.add(new HotelItem("Hôtel Plaza Athénée", "4.8", "Paris", R.drawable.default_image));
                hotelList.add(new HotelItem("Le Negresco", "4.7", "Nice", R.drawable.default_image));
                break;

            case "Germany":
                hotelList.add(new HotelItem("Berlin Central Hotel", "4.5", "Berlin", R.drawable.default_image));
                hotelList.add(new HotelItem("Munich Grand Inn", "4.6", "Munich", R.drawable.default_image));
                hotelList.add(new HotelItem("Frankfurt Skyline Hotel", "4.4", "Frankfurt", R.drawable.default_image));
                break;

            case "Greece":
                hotelList.add(new HotelItem("Athens Royal Suites", "4.7", "Athens", R.drawable.default_image));
                hotelList.add(new HotelItem("Santorini Sunset Hotel", "4.8", "Santorini", R.drawable.default_image));
                hotelList.add(new HotelItem("Crete Beach Resort", "4.6", "Crete", R.drawable.default_image));
                break;

            case "Hungary":
                hotelList.add(new HotelItem("Budapest River Hotel", "4.5", "Budapest", R.drawable.default_image));
                hotelList.add(new HotelItem("Hungarian Palace Inn", "4.4", "Budapest", R.drawable.default_image));
                hotelList.add(new HotelItem("Danube Grand Hotel", "4.6", "Budapest", R.drawable.default_image));
                break;

            case "Ireland":
                hotelList.add(new HotelItem("Dublin Castle Hotel", "4.3", "Dublin", R.drawable.default_image));
                hotelList.add(new HotelItem("Emerald Isle Inn", "4.5", "Dublin", R.drawable.default_image));
                hotelList.add(new HotelItem("Cork River Lodge", "4.4", "Cork", R.drawable.default_image));
                break;

            case "Latvia":
                hotelList.add(new HotelItem("Riga Center Hotel", "4.2", "Riga", R.drawable.default_image));
                hotelList.add(new HotelItem("Latvian Boutique Hotel", "4.3", "Riga", R.drawable.default_image));
                hotelList.add(new HotelItem("Baltic Sea Inn", "4.1", "Liepaja", R.drawable.default_image));
                break;

            case "Lithuania":
                hotelList.add(new HotelItem("Vilnius Plaza Hotel", "4.6", "Vilnius", R.drawable.default_image));
                hotelList.add(new HotelItem("Lithuanian Grand Inn", "4.5", "Kaunas", R.drawable.default_image));
                hotelList.add(new HotelItem("Old Town Hotel", "4.4", "Vilnius", R.drawable.default_image));
                break;

            case "Luxembourg":
                hotelList.add(new HotelItem("Luxembourg Palace Hotel", "4.8", "Luxembourg City", R.drawable.default_image));
                hotelList.add(new HotelItem("Grand Duchy Suites", "4.6", "Luxembourg City", R.drawable.default_image));
                hotelList.add(new HotelItem("Moselle River Inn", "4.5", "Remich", R.drawable.default_image));
                break;

            case "Malta":
                hotelList.add(new HotelItem("Valletta Sea View", "4.5", "Valletta", R.drawable.default_image));
                hotelList.add(new HotelItem("Mdina Boutique Hotel", "4.4", "Mdina", R.drawable.default_image));
                hotelList.add(new HotelItem("Blue Lagoon Inn", "4.6", "Comino", R.drawable.default_image));
                break;

            case "Netherlands":
                hotelList.add(new HotelItem("Amsterdam Canal Suites", "4.7", "Amsterdam", R.drawable.default_image));
                hotelList.add(new HotelItem("Rotterdam City Inn", "4.4", "Rotterdam", R.drawable.default_image));
                hotelList.add(new HotelItem("Tulip Garden Hotel", "4.5", "Lisse", R.drawable.default_image));
                break;

            case "Poland":
                hotelList.add(new HotelItem("Warsaw City Center", "4.5", "Warsaw", R.drawable.default_image));
                hotelList.add(new HotelItem("Krakow Castle View", "4.6", "Krakow", R.drawable.default_image));
                hotelList.add(new HotelItem("Gdansk Old Town Hotel", "4.4", "Gdansk", R.drawable.default_image));
                break;

            case "Portugal":
                hotelList.add(new HotelItem("Lisbon View Hotel", "4.7", "Lisbon", R.drawable.default_image));
                hotelList.add(new HotelItem("Porto River Suites", "4.5", "Porto", R.drawable.default_image));
                hotelList.add(new HotelItem("Algarve Beach Inn", "4.6", "Algarve", R.drawable.default_image));
                break;

            case "Romania":
                hotelList.add(new HotelItem("Bucharest Central", "4.4", "Bucharest", R.drawable.default_image));
                hotelList.add(new HotelItem("Transylvania Grand Hotel", "4.3", "Brasov", R.drawable.default_image));
                hotelList.add(new HotelItem("Black Sea Resort", "4.5", "Constanta", R.drawable.default_image));
                break;

            case "Slovakia":
                hotelList.add(new HotelItem("Bratislava River Hotel", "4.6", "Bratislava", R.drawable.default_image));
                hotelList.add(new HotelItem("Tatra Mountains Lodge", "4.5", "Poprad", R.drawable.default_image));
                hotelList.add(new HotelItem("Slovak Garden Inn", "4.4", "Kosice", R.drawable.default_image));
                break;

            case "Slovenia":
                hotelList.add(new HotelItem("Ljubljana Center Hotel", "4.5", "Ljubljana", R.drawable.default_image));
                hotelList.add(new HotelItem("Lake Bled Resort", "4.8", "Bled", R.drawable.default_image));
                hotelList.add(new HotelItem("Piran Seaside Inn", "4.4", "Piran", R.drawable.default_image));
                break;

            case "Spain":
                hotelList.add(new HotelItem("Barcelona City Inn", "4.6", "Barcelona", R.drawable.default_image));
                hotelList.add(new HotelItem("Madrid Royal Suites", "4.5", "Madrid", R.drawable.default_image));
                hotelList.add(new HotelItem("Seville Historic Hotel", "4.4", "Seville", R.drawable.default_image));
                break;

            case "Sweden":
                hotelList.add(new HotelItem("Stockholm Harbor Hotel", "4.6", "Stockholm", R.drawable.default_image));
                hotelList.add(new HotelItem("Gothenburg Grand", "4.5", "Gothenburg", R.drawable.default_image));
                hotelList.add(new HotelItem("Malmö Center Suites", "4.4", "Malmö", R.drawable.default_image));
                break;

            default:
                hotelList.add(new HotelItem("Generic Hotel", "4.0", "Unknown", R.drawable.default_image));
                hotelList.add(new HotelItem("City Center Inn", "4.1", "Unknown", R.drawable.default_image));
                hotelList.add(new HotelItem("River View Hotel", "4.2", "Unknown", R.drawable.default_image));
                break;



        }

        hotelAdapter = new HotelAdapter(hotelList);
        hotelRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelRecyclerView.setAdapter(hotelAdapter);
    }
}
