package com.kawesi.sugarcakes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kawesi.sugarcakes.data.CakeDbHelper;
import com.kawesi.sugarcakes.data.DummyCakesData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabButton;

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

        fabButton = (FloatingActionButton) findViewById(R.id.fab_createcake);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
