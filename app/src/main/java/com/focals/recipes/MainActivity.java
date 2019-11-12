package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ArrayList<Recipe> recipeList = RecipeJsonParser.getRecipes(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        RecipeAdapter recipeAdapter = new RecipeAdapter(this, R.layout.activity_main_single, recipeList);

        listView.setAdapter(recipeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recipe recipe  = recipeList.get(position);

                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);


                // Name

                intent.putExtra("name", recipe.getName());



                // Ingredients List
                intent.putExtra("ingredients", recipe.getIngredients());



                // Steps List
                intent.putExtra("steps", recipe.getSteps());


                startActivity(intent);


            }
        });


    }
}
