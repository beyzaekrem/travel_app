package com.example.travel_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginBtn, goToRegisterBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        goToRegisterBtn = findViewById(R.id.goToRegister);

        loginBtn.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (db.checkUser(email, password)) {

                SharedPreferences loginPrefs = getSharedPreferences("activeUser", MODE_PRIVATE);
                SharedPreferences.Editor editor = loginPrefs.edit();
                editor.putString("email", email);
                editor.apply();

                Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Hatalı e-posta veya şifre", Toast.LENGTH_SHORT).show();
            }
        });

        goToRegisterBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
