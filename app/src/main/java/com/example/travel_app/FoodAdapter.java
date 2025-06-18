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

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private ArrayList<FoodItem> foodList;
    private Context context;

    public FoodAdapter(Context context, ArrayList<FoodItem> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem currentItem = foodList.get(position);
        holder.foodName.setText(currentItem.getName());
        holder.foodImage.setImageResource(currentItem.getImageResource());
        holder.restaurants.setText(String.join("\n", currentItem.getRestaurants()));

        // ℹ️ info simgesine tıklanınca açıklama AlertDialog'u göster
        holder.infoIcon.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(currentItem.getName());
            builder.setMessage(currentItem.getDescription());
            builder.setPositiveButton("TAMAM", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage, infoIcon;
        TextView foodName, restaurants;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            restaurants = itemView.findViewById(R.id.restaurants);
            infoIcon = itemView.findViewById(R.id.infoIcon); // 🔔 info butonu bağlandı
        }
    }
}

