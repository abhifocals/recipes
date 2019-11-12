package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_recipe);

        String name = getIntent().getStringExtra("name");

        ArrayList<HashMap<String, String>> ingredients = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("ingredients");

        ArrayList<HashMap<String, String>> steps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("steps");

        System.out.println();

    }
}
