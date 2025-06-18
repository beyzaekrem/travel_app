package com.example.travel_app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.ImageView;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodActivity extends AppCompatActivity {

    private TextView countryName;
    private ImageView countryImage;
    private RecyclerView foodRecyclerView;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        countryName = findViewById(R.id.countryName);
        countryImage = findViewById(R.id.countryImage);
        foodRecyclerView = findViewById(R.id.foodRecyclerView);

        String country = getIntent().getStringExtra("country");
        if (country == null) {
            Log.e("FoodActivity", "Country değeri null geldi!");
            country = "Italy";  // Varsayılan olarak İtalya'yı belirle
        }
        countryName.setText(country);

        int imageRes = getCountryImage(country);
        countryImage.setImageResource(imageRes);

        ArrayList<FoodItem> foodList = getFoodList(country);

        foodAdapter = new FoodAdapter(this, foodList);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerView.setAdapter(foodAdapter);
    }

    private int getCountryImage(String country) {
        if (country == null) return R.drawable.default_image;
        switch (country) {
            case "Austria":
                return R.drawable.austria;
            case "Belgium":
                return R.drawable.belgium;
            case "Bulgaria":
                return R.drawable.bulgaria;
            case "Croatia":
                return R.drawable.croatia;
            case "Czechia":
                return R.drawable.czechia;

            case "Italy":
                return R.drawable.italy;
            case "France":
                return R.drawable.france;
            case "Spain":
                return R.drawable.spain;
            default:
                return R.drawable.default_image;
        }
    }

    private ArrayList<FoodItem> getFoodList(String country) {
        ArrayList<FoodItem> foodList = new ArrayList<>();

        switch (country) {
            case "Italy":
                foodList.add(new FoodItem("Pizza", R.drawable.pizza,
                        new String[]{"Da Cicero - 4.9", "Pepe in Grani - 4.8", "Apud Jatum Panormus - 4.6"},
                        "Dünya çapında bilinen yuvarlak ve ince hamurlu İtalyan yemeği."));
                foodList.add(new FoodItem("Pasta", R.drawable.pasta,
                        new String[]{"DOC EnoBistrot - 4.7", "al42 by Pasta Chef Monti - 4.7", "Come'na Vorta - 4.6"},
                        "Makarnanın İtalya'ya özgü geleneksel ve zengin soslu hali."));
                break;

            case "France":
                foodList.add(new FoodItem("Croissant", R.drawable.croissant,
                        new String[]{"Le Grenier à Pain - 4.8", "Du Pain et des Idées - 4.7", "La Parisienne - 4.6"},
                        "Tereyağlı kat kat hamurdan yapılan, Fransız kahvaltısının vazgeçilmezi."));
                foodList.add(new FoodItem("Quiche", R.drawable.quiche,
                        new String[]{"Quiche Café - 4.7", "Au Pied de Cochon - 4.6", "Les Quiches de Mamie - 4.5"},
                        "Yumurta, süt ve sebzelerle yapılan tuzlu tart türü."));
                break;

            case "Austria":
                foodList.add(new FoodItem("Schnitzel", R.drawable.schnitzel,
                        new String[]{"Figlmüller - 4.9", "Gasthaus Pöschl - 4.8", "Zum Schwarzen Kameel - 4.7"},
                        "İnce dövülmüş etin pane harcıyla kızartıldığı klasik Viyana yemeği."));
                foodList.add(new FoodItem("Apfelstrudel", R.drawable.apfelstrudel,
                        new String[]{"Café Central - 4.8", "Demel - 4.7", "Landtmann - 4.6"},
                        "Elmalı ve tarçınlı Avusturya'ya özgü tatlı bir hamur işi."));
                break;

            case "Bulgaria":
                foodList.add(new FoodItem("Banitsa", R.drawable.banitsa,
                        new String[]{"Hadjidraganov's Houses - 4.8", "Made in Home - 4.7", "Moma Bulgarian Food & Wine - 4.6"},
                        "Yufka, yumurta ve peynirle yapılan Bulgaristan'a özgü geleneksel bir börek."));
                foodList.add(new FoodItem("Kavarma", R.drawable.kavarma,
                        new String[]{"Sasa Asian Pub - 4.7", "Chevermeto - 4.6", "Raketa Rakia Bar - 4.5"},
                        "Et, soğan ve sebzelerin güveçte pişirilmesiyle hazırlanan doyurucu bir yemektir."));
                break;

            case "Belgium":
                foodList.add(new FoodItem("Waffle", R.drawable.waffle,
                        new String[]{"Maison Dandoy - 4.8", "Vitalgaufre - 4.7", "Le Funambule - 4.6"},
                        "Tatlı hamurdan yapılan ve üzerine meyve, çikolata eklenen Belçika’nın ikonik tatlısı."));
                foodList.add(new FoodItem("Moules-frites", R.drawable.moules_frites,
                        new String[]{"La Roue d'Or - 4.7", "Noordzee Mer du Nord - 4.6", "Chez Léon - 4.5"},
                        "Midye ve patates kızartması ile servis edilen geleneksel Belçika yemeği."));
                break;

            case "Croatia":
                foodList.add(new FoodItem("Peka", R.drawable.peka,
                        new String[]{"Konoba Mate - 4.8", "Konoba Didov San - 4.7", "Konoba Dalmatino - 4.6"},
                        "Et ve sebzelerin köz ateşinde saatlerce ağır ağır pişirildiği geleneksel bir yemektir."));
                foodList.add(new FoodItem("Pašticada", R.drawable.pasticada,
                        new String[]{"Konoba Varos - 4.7", "Konoba Marjan - 4.6", "Villa Spiza - 4.5"},
                        "Şarap, sirke ve baharatlarla marine edilmiş dana etiyle yapılan Dubrovnik spesiyali."));
                break;

            case "Czechia":
                foodList.add(new FoodItem("Svickova", R.drawable.svickova,
                        new String[]{"Cafe Louvre - 4.8", "U Medvidku - 4.7", "Mincovna - 4.6"},
                        "Havuç, soğan ve krema ile hazırlanan sosun içinde sunulan marine dana eti yemeğidir."));
                foodList.add(new FoodItem("Goulash", R.drawable.goulash,
                        new String[]{"U Fleku - 4.7", "U Parlamentu - 4.6", "U Pinkasu - 4.5"},
                        "Macar kökenli olsa da Çek mutfağında da yaygın, yoğun baharatlı et güveci."));
                break;

            case "Denmark":
                foodList.add(new FoodItem("Smørrebrød", R.drawable.smorrebrod,
                        new String[]{"Aamanns - 4.8", "Schønnemann - 4.7", "Restaurant Kronborg - 4.6"},
                        "Çeşitli malzemelerle süslenen açık yüzlü sandviçler, Danimarka mutfağının temelidir."));
                foodList.add(new FoodItem("Stegt Flæsk", R.drawable.stegt_flaesk,
                        new String[]{"Restaurant Puk - 4.7", "Tivolihallen - 4.6", "Kødbyens Fiskebar - 4.5"},
                        "Kızarmış domuz eti, patates ve maydanoz sosu ile sunulan ulusal yemektir."));
                break;

            case "Estonia":
                foodList.add(new FoodItem("Verivorst", R.drawable.verivorst,
                        new String[]{"Olde Hansa - 4.8", "Rataskaevu 16 - 4.7", "Leib Resto ja Aed - 4.6"},
                        "Kan sosisi olarak bilinen geleneksel Noel yemeği, genellikle lahana ile servis edilir."));
                foodList.add(new FoodItem("Kohuke", R.drawable.kohuke,
                        new String[]{"Cafe Maiasmokk - 4.7", "Kompressor - 4.6", "Kohvik Komeet - 4.5"},
                        "Tatlı lor peynirinin çikolata kaplamasıyla sunulduğu popüler atıştırmalıktır."));
                break;

            case "Finland":
                foodList.add(new FoodItem("Karjalanpaisti", R.drawable.karjalanpaisti,
                        new String[]{"Ravintola Savotta - 4.8", "Kappeli - 4.7", "Sea Horse - 4.6"},
                        "Dana, domuz ve kuzu etlerinin fırında uzun süre pişirilmesiyle yapılan et yemeği."));
                foodList.add(new FoodItem("Kalakukko", R.drawable.kalakukko,
                        new String[]{"Ravintola Nokka - 4.7", "Lappi Ravintola - 4.6", "Juuri - 4.5"},
                        "Balık ve domuz etiyle doldurulan geleneksel Fin ekmeği."));
                break;

            case "Germany":
                foodList.add(new FoodItem("Bratwurst", R.drawable.bratwurst,
                        new String[]{"Curry 36 - 4.8", "Wursterei - 4.7", "Zum Bratwurstherzle - 4.6"},
                        "Almanya'nın en bilinen sosis türlerinden biri, genellikle ızgarada pişirilir."));
                foodList.add(new FoodItem("Sauerkraut", R.drawable.sauerkraut,
                        new String[]{"Augustiner Bräustuben - 4.7", "Hofbräuhaus - 4.6", "Zum Franziskaner - 4.5"},
                        "Fermente lahana ile hazırlanan ekşi garnitür, et yemeklerinin yanında sunulur."));
                break;

            default:
                foodList.add(new FoodItem(
                        "Local Dish",
                        R.drawable.default_image,
                        new String[]{"Local Restaurant 1 - 4.5", "Local Restaurant 2 - 4.4", "Local Restaurant 3 - 4.3"},
                        "Bu ülkeye özel bir yemek bilgisi bulunamadı. Ancak yerel restoranlarda popüler yöresel tatlar deneyebilirsiniz."
                ));
                break;

        }

        return foodList;
    }
}
