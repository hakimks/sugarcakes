package com.kawesi.sugarcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kawesi.sugarcakes.data.CakeDbHelper;

public class MainActivity extends AppCompatActivity {
    CakeDbHelper mCakeDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCakeDbHelper = new CakeDbHelper(this);


    }
}
