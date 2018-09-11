package com.gemini.starwar.utils;

/**
 * Created by geminih on 9/11/2018.
 */

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


import com.gemini.starwar.database.AppDatabase;
import com.gemini.starwar.database.entity.CharacterEntity;

import java.util.List;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static CharacterEntity addUser(final AppDatabase db, CharacterEntity user) {
        db.userDao().insertAll(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db) {
        CharacterEntity user = new CharacterEntity();
        user.setmName("Ajay");

        addUser(db, user);

        List<CharacterEntity> userList = db.userDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}