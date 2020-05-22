package com.example.diplom;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.diplom.data.DbHelper;
import com.example.diplom.data.Keystore;
import com.example.diplom.data.NoteStore;
import com.example.diplom.repository.DbKeystore;
import com.example.diplom.repository.DbNoteStore;

public class App extends Application {
    public static DbKeystore keystore;
    public static DbNoteStore noteHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper db = new DbHelper(getApplicationContext());
        SQLiteDatabase database = db.getWritableDatabase();
        keystore = new DbKeystore(database);
        noteHolder = new DbNoteStore(database);
    }

    public static Keystore getKeystore() {
        return keystore;
    }

    public static NoteStore getNoteHolder() {
        return noteHolder;
    }
}
