package com.kawesi.sugarcakes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CakeDao {

    @Insert
    void insertCake(Cake cake);

    @Query("DELETE FROM cake_table")
    void deleteAllCakes();

    @Query("SELECT * FROM cake_table")
    LiveData<ArrayList<Cake>> getAllCakes();


}
