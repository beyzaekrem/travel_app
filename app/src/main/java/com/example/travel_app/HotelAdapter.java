package com.example.travel_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<HotelItem> hotelList;

    public HotelAdapter(List<HotelItem> hotelList) {
        this.hotelList = hotelList;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        HotelItem item = hotelList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.ratingTextView.setText("Rating: " + item.getRating());
        holder.locationTextView.setText("Location: " + item.getLocation());
        holder.imageView.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, ratingTextView, locationTextView;
        ImageView imageView;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.hotelName);
            ratingTextView = itemView.findViewById(R.id.hotelRating);
            locationTextView = itemView.findViewById(R.id.hotelLocation);
            imageView = itemView.findViewById(R.id.hotelImage);
        }
    }
}