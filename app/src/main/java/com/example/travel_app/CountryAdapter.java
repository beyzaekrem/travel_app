package com.example.travel_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private List<Country> countryList;
    private Context context;

    // Constructor (Yapıcı Metot)
    public CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.btnCountry.setText(country.getName());

        // Butona tıklanınca detay sayfasına yönlendirme
        holder.btnCountry.setOnClickListener(v -> {
            Intent intent = new Intent(context, CountryDetailActivity.class);
            intent.putExtra("country_name", country.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btnCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCountry = itemView.findViewById(R.id.btnCountry);
        }
    }
}
