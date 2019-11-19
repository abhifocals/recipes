package com.focals.recipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.focals.recipes.adapter.MainAdapter;
import com.focals.recipes.R;
import com.focals.recipes.utils.Recipe;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.gridView)
    GridView gridView;

    ArrayList<Recipe> recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recipeList = RecipeJsonParser.parseRecipes(this);

        MainAdapter mainAdapter = new MainAdapter(this, R.layout.activity_main_singlerecipe, recipeList);

        gridView.setAdapter(mainAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Recipe recipe = recipeList.get(position);

                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);

                // Name
                intent.putExtra("name", recipe.getName());

                // Ingredients List
                intent.putExtra("ingredients", recipe.getIngredients());

                // Steps List
                intent.putExtra("steps", recipe.getSteps());

                intent.putExtra("recipePosition", position);

                startActivity(intent);
            }
        });
    }
}
