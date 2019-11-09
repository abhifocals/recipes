package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_detail);

        String name = getIntent().getStringExtra("name");

        ArrayList<HashMap<String, String>> ingredients = (ArrayList<HashMap<String, String>>) getIntent().getParcelableExtra("ingredients");

        ArrayList<HashMap<String, String>> steps = (ArrayList<HashMap<String, String>>) getIntent().getParcelableExtra("steps");

        System.out.println();

    }
}
