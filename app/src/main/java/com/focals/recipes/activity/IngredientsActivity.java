package com.focals.recipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.ListView;

import com.focals.recipes.adapters.IngredientsAdapter;
import com.focals.recipes.R;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsActivity extends AppCompatActivity {


    @BindView(R.id.lv_ingredients)
    ListView ingredientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ingredients);
        ButterKnife.bind(this);

        // Get Ingredients from DetailActivity
        ArrayList<HashMap<String, String>> ingredientsList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("ingredients");

        // Setup IngredientAdapter
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, R.layout.activity_ingredients_singleingredient, ingredientsList);
        ingredientsListView.setDivider(null);
        ingredientsListView.setAdapter(ingredientsAdapter);

        // Set ActionBar Title
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
    }
}
