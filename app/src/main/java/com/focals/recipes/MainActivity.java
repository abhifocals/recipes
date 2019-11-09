package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.focals.recipes.utils.RecipeJsonParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecipeJsonParser.getRecipes(this);
    }
}
