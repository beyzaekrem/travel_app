package com.example.travel_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileEditActivity extends AppCompatActivity {

    private EditText editFirstName, editLastName, editEmail;
    private Button btnSave;
    private ImageView avatarFemale, avatarMale;
    private SharedPreferences sharedPreferences;
    private String selectedAvatar = "female"; // Varsayılan avatar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editEmail = findViewById(R.id.editEmail);
        btnSave = findViewById(R.id.btnSave);
        avatarFemale = findViewById(R.id.avatarFemale);
        avatarMale = findViewById(R.id.avatarMale);
        sharedPreferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);

        loadUserData();

        avatarFemale.setOnClickListener(v -> {
            selectedAvatar = "female";
            updateAvatarSelection();
        });

        avatarMale.setOnClickListener(v -> {
            selectedAvatar = "male";
            updateAvatarSelection();
        });

        btnSave.setOnClickListener(v -> validateAndSaveUserData());
    }

    private void loadUserData() {
        editFirstName.setText(sharedPreferences.getString("firstName", ""));
        editLastName.setText(sharedPreferences.getString("lastName", ""));
        editEmail.setText(sharedPreferences.getString("email", ""));
        selectedAvatar = sharedPreferences.getString("profileAvatar", "female");
        updateAvatarSelection();
    }

    private void updateAvatarSelection() {
        if (selectedAvatar.equals("female")) {
            avatarFemale.setBackgroundResource(R.drawable.avatar_border_selected);
            avatarMale.setBackgroundResource(R.drawable.avatar_border_unselected);
        } else {
            avatarFemale.setBackgroundResource(R.drawable.avatar_border_unselected);
            avatarMale.setBackgroundResource(R.drawable.avatar_border_selected);
        }
    }

    private void validateAndSaveUserData() {
        String firstName = editFirstName.getText().toString().trim();
        String lastName = editLastName.getText().toString().trim();
        String newEmail = editEmail.getText().toString().trim();

        if (firstName.isEmpty()) {
            editFirstName.setError("Ad boş bırakılamaz!");
            return;
        }
        if (lastName.isEmpty()) {
            editLastName.setError("Soyad boş bırakılamaz!");
            return;
        }
        if (newEmail.isEmpty()) {
            editEmail.setError("E-posta boş bırakılamaz!");
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
            editEmail.setError("Lütfen geçerli bir e-posta adresi girin!");
            return;
        }

        if (!firstName.matches("[a-zA-ZğüşıöçĞÜŞİÖÇ ]+")) {
            editFirstName.setError("Ad yalnızca harf içerebilir!");
            return;
        }

        if (!lastName.matches("[a-zA-ZğüşıöçĞÜŞİÖÇ ]+")) {
            editLastName.setError("Soyad yalnızca harf içerebilir!");
            return;
        }

        // 📌 SQLite'ta e-posta güncelleme
        String oldEmail = sharedPreferences.getString("email", "");
        if (!oldEmail.equals(newEmail)) {
            DatabaseHelper db = new DatabaseHelper(this);
            boolean updated = db.updateUserEmail(oldEmail, newEmail);
            if (!updated) {
                Toast.makeText(this, "E-posta veritabanında güncellenemedi!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // ✅ SharedPreferences güncelle
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName", firstName);
        editor.putString("lastName", lastName);
        editor.putString("email", newEmail);
        editor.putString("profileAvatar", selectedAvatar);
        editor.apply();

        Toast.makeText(this, "Bilgiler başarıyla güncellendi!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileEditActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
