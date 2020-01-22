package com.kawesi.sugarcakes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CakeViewModel extends AndroidViewModel {
    private CakeRepository mCakeRepository;
    private LiveData<List<Cake>> mAllCakes;

    public CakeViewModel(@NonNull Application application) {
        super(application);

        mCakeRepository = new CakeRepository(application);
        mAllCakes = mCakeRepository.getAllCakes();
    }

    LiveData<List<Cake>> getAllCakes(){
        return mAllCakes;
    }

    public void insertCake(Cake cake){
        mCakeRepository.insertCake(cake);
    }
}
