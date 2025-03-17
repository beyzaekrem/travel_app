package com.example.travel_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private final List<String> cityList;
    private final OnCityClickListener onCityClickListener;

    public interface OnCityClickListener {
        void onCityClick(String city);
    }

    public CityAdapter(List<String> cityList, OnCityClickListener listener) {
        this.cityList = cityList;
        this.onCityClickListener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_button, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        String city = cityList.get(position);
        holder.cityButton.setText(city);
        holder.cityButton.setOnClickListener(v -> onCityClickListener.onCityClick(city));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        Button cityButton;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityButton = itemView.findViewById(R.id.cityButton);
        }
    }
}
