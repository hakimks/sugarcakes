package com.kawesi.sugarcakes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CakeDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "CakeDbHelper";
    //name of database
    public static final String DATABASE_NAME = "cakes";
    public static final int DATABASE_VERSION = 2;


    public CakeDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CAKES_TABLE = "CREATE TABLE " + CakeContract.CakeEntry.CAKE_TABLE + "( " +
                CakeContract.CakeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CakeContract.CakeEntry.CAKE_NAME + " TEXT NOT NULL, " +
                CakeContract.CakeEntry.CAKE_INGREDIENTS + " TEXT , " +
                CakeContract.CakeEntry.CAKE_PRICE + " INTEGER, " +
                CakeContract.CakeEntry.CAKE_CATEGORY + "INTEGER , " +
                CakeContract.CakeEntry.DATE_CREATED + "INTEGER NOT NULL " + " ); ";

        db.execSQL(CREATE_CAKES_TABLE);
        Log.i(TAG, "onCreate: Datbase created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CakeContract.CakeEntry.CAKE_TABLE);
        onCreate(db);
    }
}
