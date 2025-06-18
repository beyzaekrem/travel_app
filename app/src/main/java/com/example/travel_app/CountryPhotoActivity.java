package com.example.travel_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class CountryPhotoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private String country;
    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private ArrayList<File> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_photo);

        country = getIntent().getStringExtra("country");
        if (country == null) country = "Italy";

        TextView title = findViewById(R.id.countryTitle);
        Button addPhotoBtn = findViewById(R.id.addPhotoBtn);
        recyclerView = findViewById(R.id.recyclerViewPhotos);

        title.setText("Photos from " + country);

        photoList = loadPhotos();
        photoAdapter = new PhotoAdapter(photoList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(photoAdapter);
        photoAdapter.notifyDataSetChanged();

        addPhotoBtn.setOnClickListener(v -> openGallery());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            saveImageToInternalStorage(imageUri);
        }
    }

    private void saveImageToInternalStorage(Uri imageUri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            File dir = new File(getExternalFilesDir(null), "photos/" + country);
            if (!dir.exists()) dir.mkdirs();

            String fileName = "photo_" + System.currentTimeMillis() + ".jpg";
            File photoFile = new File(dir, fileName);

            FileOutputStream fos = new FileOutputStream(photoFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();

            photoList.add(photoFile);
            photoAdapter.notifyItemInserted(photoList.size() - 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<File> loadPhotos() {
        ArrayList<File> list = new ArrayList<>();
        File dir = new File(getExternalFilesDir(null), "photos/" + country);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    list.add(file);
                }
            }
        }
        return list;
    }
}
