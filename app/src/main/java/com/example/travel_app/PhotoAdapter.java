package com.example.travel_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<File> photoList;
    private Context context;

    public PhotoAdapter(List<File> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext(); // context set edilir
        View view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        File photoFile = photoList.get(position);
        if (photoFile.exists()) {
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(photoFile.getAbsolutePath()));
        }

        // Tıklanınca full ekran göster
        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PhotoPreviewActivity.class);
            intent.putExtra("imagePath", photoFile.getAbsolutePath());
            context.startActivity(intent);
        });

        // Uzun basınca sil
        holder.imageView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Photo")
                    .setMessage("Are you sure you want to delete this photo?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (photoFile.delete()) {
                            photoList.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(context, "Photo deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photoImageView);
        }
    }
}
