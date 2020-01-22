package com.kawesi.sugarcakes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kawesi.sugarcakes.data.CakeDbHelper;
import com.kawesi.sugarcakes.data.DummyCakesData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int CREATE_REQUEST_CODE = 1;
    FloatingActionButton fabButton;

    CakeDbHelper mCakeDbHelper;
    List<Cake> cakesList;
    private CakeViewModel mCakeViewModel;
    private RecyclerView cakesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cakesRecyclerView = (RecyclerView) findViewById(R.id.cake_recycler_view);

        final CakeListAdapter cakeListAdapter = new CakeListAdapter(cakesList);

        cakesRecyclerView.setAdapter(cakeListAdapter);
        cakesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCakeViewModel = new ViewModelProvider(this).get(CakeViewModel.class);
        mCakeViewModel.getAllCakes().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(List<Cake> cakes) {
                cakeListAdapter.setCakes(cakes);
            }
        });

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
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_REQUEST_CODE && resultCode == RESULT_OK){

        }
    }
}
