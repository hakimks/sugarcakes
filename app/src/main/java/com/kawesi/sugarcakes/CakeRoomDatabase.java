package com.kawesi.sugarcakes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Cake.class}, version = 1, exportSchema = false)
public abstract class CakeRoomDatabase extends RoomDatabase {
    public abstract CakeDao mCakeDao();

    private static volatile CakeRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriterService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CakeRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (CakeRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CakeRoomDatabase.class, "cake_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
