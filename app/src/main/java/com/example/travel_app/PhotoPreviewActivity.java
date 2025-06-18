package com.example.travel_app;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PhotoPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);

        ImageView imageView = findViewById(R.id.fullscreenImageView);
        String imagePath = getIntent().getStringExtra("imagePath");

        if (imagePath != null) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        }
    }
}
