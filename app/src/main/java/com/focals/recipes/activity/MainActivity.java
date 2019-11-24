package com.focals.recipes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.focals.recipes.R;
import com.focals.recipes.adapter.MainAdapter;
import com.focals.recipes.utils.Recipe;
import com.focals.recipes.utils.RecipeJsonParser;
import com.focals.recipes.widget.RecipeWidgetProvider;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        MainAdapter mainAdapter = new MainAdapter(this, R.layout.recipe, recipeList);
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
                intent.putExtra(getString(R.string.recipe_name), recipe.getName());

                // Ingredients List used by IngredientsActivity
                intent.putExtra(getString(R.string.ingredients_list), recipe.getIngredients());

                // Steps List used by StepActivity
                intent.putExtra(getString(R.string.steps_list), recipe.getSteps());

                // Used by StepFragment
                intent.putExtra(getString(R.string.recipe_position), position);

                startActivity(intent);

                // Send recipe info to WidgetProvider
                Intent widgetIntent = new Intent(MainActivity.this, RecipeWidgetProvider.class);
                widgetIntent.setAction(RecipeWidgetProvider.RECIPE_INTENT);
                widgetIntent.putExtra(getString(R.string.recipe_position), position);
                sendBroadcast(widgetIntent);
            }
        });
    }
}
