package com.focals.recipes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.focals.recipes.R;
import com.focals.recipes.adapter.StepAdapter;
import com.focals.recipes.fragment.IngredientsDetailFragment;
import com.focals.recipes.fragment.StepFragment;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements StepAdapter.StepClickHandler {

    private String name;
    private ArrayList<String> ingredients;
    private ArrayList<HashMap<String, String>> steps;
    private int recipePosition;

    @BindView(R.id.rv_steps)
    RecyclerView stepsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);

        // Binding view with Butterknife
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Getting recipe position from MainActivity
        recipePosition = getIntent().getIntExtra(getString(R.string.recipe_position), -1);

        name = getIntent().getStringExtra(getString(R.string.recipe_name));
//        ingredients = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra(getString(R.string.ingredients_list));
        ingredients = RecipeJsonParser.getRecipes().get(recipePosition).getIngredients1();
        steps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra(getString(R.string.steps_list));

        // Setup StepAdapter
        StepAdapter stepAdapter = new StepAdapter(steps, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        stepsRecyclerView.setAdapter(stepAdapter);
        stepsRecyclerView.setLayoutManager(linearLayoutManager);

        // Set ActionBar Title
        getSupportActionBar().setTitle(name);


    }

    /**
     * Used by Ingredients button via layout.
     *
     * @param v
     */
    public void showIngredients(View v) {

        // If Tablet, create Ingredient Fragment explicity.
        if (isTablet()) {
            IngredientsDetailFragment ingredientsDetailFragment = new IngredientsDetailFragment(ingredients);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tabletRight, ingredientsDetailFragment).commit();
        }
        // Else start IngredientDetailActivity
        else {
            Intent intent = new Intent(this, IngredientsDetailActivity.class);
            intent.putExtra(getString(R.string.ingredients_list), ingredients);
            intent.putExtra(getString(R.string.recipe_name), name);

            startActivity(intent);
        }
    }

    @Override
    public void onStepClick(int stepPosition) {

        // If Tablet, create Step Fragment explicity.
        if (isTablet()) {
            StepFragment stepFragment = new StepFragment(recipePosition, stepPosition);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tabletRight, stepFragment).commit();
        }
        // Else start StepActivity
        else {
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(getString(R.string.step_position), stepPosition);
            intent.putExtra(getString(R.string.recipe_position), recipePosition);

            startActivity(intent);
        }
    }

    ////// Helper /////

    private boolean isTablet() {

        if (findViewById(R.id.fragment_recipeDetail_tablet) != null) {
            return true;
        }

        return false;
    }
}