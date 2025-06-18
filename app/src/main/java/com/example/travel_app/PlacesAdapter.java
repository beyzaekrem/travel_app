package com.example.travel_app;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private List<PlacesItem> placeList;
    private Context context;

    public PlacesAdapter(Context context, List<PlacesItem> placeList) {
        this.context = context;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_item, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        PlacesItem item = placeList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.locationTextView.setText("Location: " + item.getLocation());
        holder.imageView.setImageResource(item.getImageResId());

        // 🔍 infoIcon'a tıklanınca AlertDialog göster
        holder.infoIcon.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(item.getName());
            builder.setMessage("Konum: " + item.getLocation() + "\n\n" + item.getDescription());
            builder.setPositiveButton("Tamam", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, locationTextView;
        ImageView imageView, infoIcon;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.placeName);
            locationTextView = itemView.findViewById(R.id.placeLocation);
            imageView = itemView.findViewById(R.id.placeImage);
            infoIcon = itemView.findViewById(R.id.infoIcon); // 🔔 info simgesi bağlandı
        }
    }
}
