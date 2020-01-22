package com.kawesi.sugarcakes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CakeRepository {
    private CakeDao mCakeDao;
    private LiveData<List<Cake>> mAllCakes;

    CakeRepository(Application application){
        CakeRoomDatabase db = CakeRoomDatabase.getDatabase(application);
        mCakeDao = db.mCakeDao();
        mAllCakes = mCakeDao.getAllCakes();
    }

    LiveData<List<Cake>> getAllCakes(){
        return mAllCakes;
    }

    void insertCake(Cake cake){
        mCakeDao.insertCake(cake);
    }
}
