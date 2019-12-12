package com.kawesi.sugarcakes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kawesi.sugarcakes.data.CakeDbHelper;
import com.kawesi.sugarcakes.data.DummyCakesData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int CREATE_REQUEST_CODE = 1;
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
                Intent createCakeIntent = new Intent(MainActivity.this, CreateCakeActivity.class);
                startActivityForResult(createCakeIntent, CREATE_REQUEST_CODE);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CREATE_REQUEST_CODE){
           if( resultCode == RESULT_OK) {
               // add the added cake on top of the list
           }
        }
    }
}
