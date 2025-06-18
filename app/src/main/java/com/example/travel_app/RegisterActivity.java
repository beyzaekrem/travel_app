package com.example.travel_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, surnameInput, emailInput, passwordInput;
    RadioGroup genderGroup;
    RadioButton radioMale, radioFemale;
    Button registerBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        nameInput = findViewById(R.id.name);
        surnameInput = findViewById(R.id.surname);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        genderGroup = findViewById(R.id.genderGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String surname = surnameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            int selectedGenderId = genderGroup.getCheckedRadioButtonId();
            String gender = "";
            if (selectedGenderId == R.id.radioMale) {
                gender = "Erkek";
            } else if (selectedGenderId == R.id.radioFemale) {
                gender = "Kadın";
            }

            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!(email.endsWith("@gmail.com") || email.endsWith("@hotmail.com") || email.endsWith("@outlook.com"))) {
                Toast.makeText(this, "Geçerli bir e-posta girin", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = db.insertUser(name, surname, gender, email, password);
            if (success) {
                // 1. Kullanıcı bilgilerini e-posta ile kaydet
                SharedPreferences userPrefs = getSharedPreferences(email, MODE_PRIVATE);
                SharedPreferences.Editor userEditor = userPrefs.edit();
                userEditor.putString("firstName", name);
                userEditor.putString("lastName", surname);
                userEditor.putString("gender", gender);
                userEditor.putString("email", email);
                userEditor.putString("profileAvatar", gender.equals("Erkek") ? "male" : "female");
                userEditor.apply();

                Toast.makeText(this, "Kayıt başarılı! Giriş yapabilirsiniz.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "Bu e-posta zaten kayıtlı olabilir!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
