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
import com.focals.recipes.utils.RecipeConstants;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gridView)
    GridView gridView;

    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding views with Butterknife
        ButterKnife.bind(this);

        // Getting recipes from parser
        recipeList = RecipeJsonParser.parseRecipes(this);

        // Setting the adapter in view
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

                // Name used by both IngredientsDetail and Step Activities
                intent.putExtra(RecipeConstants.RECIPE_NAME, recipe.getName());

                // Ingredients List used by IngredientsActivity
                intent.putExtra(RecipeConstants.INGREDIENTS_LIST, recipe.getIngredients());

                // Steps List used by StepActivity
                intent.putExtra(RecipeConstants.STEPS_LIST, recipe.getSteps());

                // Used by StepFragment
                intent.putExtra(RecipeConstants.RECIPE_POSITION, position);

                startActivity(intent);
            }
        });
    }
}
