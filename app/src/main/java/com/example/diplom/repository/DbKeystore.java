package com.example.diplom.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.diplom.data.DbHelper;
import com.example.diplom.data.Keystore;

public class DbKeystore implements Keystore {
    private SQLiteDatabase database;

    public DbKeystore(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public boolean userExists(String userName) {
        try (Cursor queryCursor = database.query(DbHelper.LOG_TABLE, new String[]{DbHelper.KEY_ADMIN},
                DbHelper.KEY_NAME + "='" + userName + "'",
                null, null, null, null)) {
            if (queryCursor != null) {
                return queryCursor.moveToFirst();
            }
        }

        return false;
    }

    @Nullable
    @Override
    public String isAdmin(String userName, String pin) {
        try (Cursor queryCursor = database.query(DbHelper.LOG_TABLE, new String[]{DbHelper.KEY_ADMIN},
                DbHelper.KEY_NAME + "='" + userName + "' and " + DbHelper.KEY_PIN + "='" + pin + "'",
                null, null, null, null)) {
            if (queryCursor != null) {
                if (queryCursor.moveToFirst()) {
                    return queryCursor.getString(queryCursor.getColumnIndex(DbHelper.KEY_ADMIN));
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void newUser(String userName, String pin) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.KEY_NAME, userName);
        contentValues.put(DbHelper.KEY_PIN, pin);
        contentValues.put(DbHelper.KEY_ADMIN, 0);
        database.insert(DbHelper.LOG_TABLE, null, contentValues);
    }
}
