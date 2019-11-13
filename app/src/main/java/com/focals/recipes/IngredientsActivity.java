package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);

        // Get Ingredients from DetailActivity
        ArrayList<HashMap<String, String>> ingredientsList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("ingredients");

        // Setup IngredientAdapter
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, R.layout.activity_ingredients_singleingredient, ingredientsList);
        ListView ingredientsListView = (ListView) findViewById(R.id.lv_ingredients);
        ingredientsListView.setDivider(null);

        ingredientsListView.setAdapter(ingredientsAdapter);
    }
}
