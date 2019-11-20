package com.focals.recipes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeConstants;
import com.focals.recipes.utils.RecipeJsonParser;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This activity is started only for Phone.
 * For Tablet, StepFragment is created.
 */

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.tv_stepDesc)
    TextView stepDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int recipePosition;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        // Binding views with Butterknife
        ButterKnife.bind(this);

        // Getting the recipe position from MainActivity
        Intent intent = getIntent();
        recipePosition = getIntent().getIntExtra(RecipeConstants.RECIPE_POSITION, -1);

        // Set ActionBar Title
        String recipeName = RecipeJsonParser.getRecipes().get(recipePosition).getName();
        getSupportActionBar().setTitle(recipeName);
    }
}
