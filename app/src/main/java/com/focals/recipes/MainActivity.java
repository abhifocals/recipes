package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Recipe> recipeList = RecipeJsonParser.getRecipes(this);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        RecipeAdapter recipeAdapter = new RecipeAdapter(this, R.layout.recipe_item, recipeList);

        gridView.setAdapter(recipeAdapter);


    }
}
