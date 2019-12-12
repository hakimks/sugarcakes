package com.kawesi.sugarcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.kawesi.sugarcakes.data.CakeCategory;

public class CreateCakeActivity extends AppCompatActivity {
    Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cake);

        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        String[] items = CakeCategory.categoryMap.values().toArray(new String[]{});

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        categorySpinner.setAdapter(adapter);
    }
}
