package com.kawesi.sugarcakes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CakeDao {

    @Insert
    public void insertCake(Cake cake);

    @Update
    public void updateCake(Cake cake);

    @Delete
    public void deleteCake(Cake cake);

    @Query("DELETE FROM cake_table")
    void deleteAllCakes();

    @Query("SELECT * FROM cake_table")
    LiveData<List<Cake>> getAllCakes();


}
