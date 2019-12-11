package com.kawesi.sugarcakes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.kawesi.sugarcakes.data.CakeDbHelper;
import com.kawesi.sugarcakes.data.DummyCakesData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CakeDbHelper mCakeDbHelper;
    ArrayList<Cake> cakesList;
    DummyCakesData mDummyCakesData = new DummyCakesData();
    private RecyclerView cakesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cakesRecyclerView = (RecyclerView) findViewById(R.id.cake_recycler_view);

        mCakeDbHelper = new CakeDbHelper(this);
        cakesList = mDummyCakesData.generateDummyCakesData();
        CakeListAdapter cakeListAdapter = new CakeListAdapter(cakesList);

        cakesRecyclerView.setAdapter(cakeListAdapter);
        cakesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
