package com.example.travel_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserData.db";
    public static final String TABLE_NAME = "users";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "GENDER";
    public static final String COL_5 = "EMAIL";
    public static final String COL_6 = "PASSWORD";

    // 📌 Versiyon 2 ile veritabanı upgrade edildi (güncelleme tetiklenir)
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT, " +
                COL_4 + " TEXT, " +
                COL_5 + " TEXT UNIQUE, " +   // E-posta benzersiz olmalı
                COL_6 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eski tabloyu sil → yeni tabloyu oluştur
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // ✅ Kullanıcı ekleme
    public boolean insertUser(String name, String surname, String gender, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, name);
        values.put(COL_3, surname);
        values.put(COL_4, gender);
        values.put(COL_5, email);
        values.put(COL_6, password);

        long result = -1;
        try {
            result = db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e("DB_ERROR", "Kayıt hatası: " + e.getMessage());
        }

        return result != -1;
    }

    // ✅ Giriş kontrolü (email + şifre)
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE EMAIL = ? AND PASSWORD = ?", new String[]{email, password});
        boolean found = cursor.getCount() > 0;
        cursor.close();
        return found;
    }

    // ✅ Aynı e-posta daha önce kayıtlı mı?
    public boolean isEmailRegistered(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE EMAIL = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // ✅ E-posta güncelleme işlemi (ProfileEdit için)
    public boolean updateUserEmail(String oldEmail, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5, newEmail);

        int rows = db.update(TABLE_NAME, values, COL_5 + " = ?", new String[]{oldEmail});
        return rows > 0;
    }

    // ✅ Kullanıcıyı e-posta adresiyle getir (Profil göstermek için kullanılabilir)
    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = ?", new String[]{email});
    }
}
