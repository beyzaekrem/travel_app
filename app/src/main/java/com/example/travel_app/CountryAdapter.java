package com.example.travel_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private List<Country> countryList;
    private Context context;
    private OnItemClickListener listener;

    // Yapıcı (Constructor)
    public CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    // Tıklama Dinleyicisi Arayüzü
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Tıklama Dinleyicisini Ayarlama
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.countryNameTextView.setText(country.getName());

        // Glide ile görsel yükleme
        Glide.with(context)
                .load(country.getFlagUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.flagImageView);

        // Tıklama olayını ayarlama
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryNameTextView;
        ImageView flagImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameTextView = itemView.findViewById(R.id.countryNameTextView);
            flagImageView = itemView.findViewById(R.id.flagImageView);

            // Tıklama işlemi
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}

