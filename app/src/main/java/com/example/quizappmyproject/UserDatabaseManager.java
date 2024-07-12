package com.example.quizappmyproject;

import static com.example.quizappmyproject.DatabaseHelper.COLUMN_USERNAME;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";

    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_GENDER = "gender";

    private SQLiteDatabase db;
    private SharedPreferences sharedPreferences;
    public UserDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void clearUserSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public void deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COLUMN_USERNAME + "=?", new String[]{username});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_GENDER + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public void insertUser(String username, String email, String password, String gender) {
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_GENDER, gender);

        db.insert(TABLE_USERS, null, values);
    }

    public boolean checkUserExistence(String username) {
        String[] columns = {KEY_ID};
        String selection = KEY_USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            return count > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String[] getUserCredentials(String username) {
        String[] credentials = new String[2]; // To store username and password

        String[] columns = {KEY_USERNAME, KEY_PASSWORD};
        String selection = KEY_USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                credentials[0] = cursor.getString(cursor.getColumnIndex(KEY_USERNAME));
                credentials[1] = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return credentials;
    }

    public String getPassword(String username) {
        String[] columns = {KEY_PASSWORD};
        String selection = KEY_USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        String password = null;
        try {
            cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return password;
    }

    public void updatePassword(String username, String newPassword) {
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, newPassword);

        String selection = KEY_USERNAME + "=?";
        String[] selectionArgs = {username};

        db.update(TABLE_USERS, values, selection, selectionArgs);
    }
}
