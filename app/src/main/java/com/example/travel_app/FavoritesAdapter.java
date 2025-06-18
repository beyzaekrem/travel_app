package com.example.travel_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private List<FavoriteItem> favoriteList;
    private List<FavoriteItem> originalList;
    private SharedPreferences prefs;

    public FavoritesAdapter(Context context, List<FavoriteItem> favoriteList) {
        this.context = context;
        this.favoriteList = new ArrayList<>(favoriteList);
        this.originalList = new ArrayList<>(favoriteList);
        this.prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorites_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteItem item = favoriteList.get(position);

        holder.countryNameText.setText(item.getCountryName());

        Glide.with(context)
                .load(item.getFlagUrl())
                .into(holder.countryFlag);

        // Ülke adına tıklanınca detay sayfasına git
        holder.countryNameText.setOnClickListener(v -> {
            Intent intent = null;
            switch (item.getCountryName()) {

                case "Austria":
                    intent = new Intent(context, AustriaActivity.class);
                    break;
                case "Belgium":
                    intent = new Intent(context, BelgiumActivity.class);
                    break;
                case "Bulgaria":
                    intent = new Intent(context, BulgariaActivity.class);
                    break;
                case "Croatia":
                    intent = new Intent(context, CroatiaActivity.class);
                    break;

                /*case "Cyprus":
                    intent = new Intent(context, CyprusActivity.class);
                    break;
*/
                case "Czechia":
                    intent = new Intent(context, CzechiaActivity.class);
                    break;

               /* case "Denmark":
                    intent = new Intent(context, DenmarkActivity.class);
                    break;

                case "Estonia":
                    intent = new Intent(context, EstoniaActivity.class);
                    break;

                case "Finland":
                    intent = new Intent(context, FinlandActivity.class);
                    break;
*/

                case "France":
                    intent = new Intent(context, FranceActivity.class);
                    break;
                case "Italy":
                    intent = new Intent(context, ItalyActivity.class);
                    break;

            }

            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        // Silme butonu
        holder.removeButton.setOnClickListener(v -> {
            prefs.edit().remove(item.getCountryName()).apply();
            favoriteList.remove(position);
            originalList.remove(item);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, favoriteList.size());
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    // 🔍 Arama filtresi
    public void filter(String query) {
        favoriteList.clear();
        if (query == null || query.trim().isEmpty()) {
            favoriteList.addAll(originalList);
        } else {
            String lowerQuery = query.toLowerCase().trim();
            for (FavoriteItem item : originalList) {
                if (item.getCountryName().toLowerCase().contains(lowerQuery)) {
                    favoriteList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    // 🔄 Listeyi dinamik olarak güncelle (örneğin loadFavorites sonrası)
    public void updateList(List<FavoriteItem> newList) {
        favoriteList.clear();
        favoriteList.addAll(newList);

        originalList.clear();
        originalList.addAll(newList);

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView countryFlag;
        TextView countryNameText;
        ImageButton removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.countryFlag);
            countryNameText = itemView.findViewById(R.id.countryNameText);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }
}
