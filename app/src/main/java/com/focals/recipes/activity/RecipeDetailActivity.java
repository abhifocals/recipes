package com.focals.recipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import com.focals.recipes.R;
import com.focals.recipes.adapter.StepAdapter;
import com.focals.recipes.fragment.IngredientsDetailFragment;
import com.focals.recipes.fragment.RecipeDetailFragment;
import com.focals.recipes.fragment.StepFragment;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeDetailActivity extends AppCompatActivity implements StepAdapter.StepClickHandler {

    private String name;
    private ArrayList<HashMap<String, String>> ingredients;
    private ArrayList<HashMap<String, String>> steps;

    @BindView(R.id.rv_steps)
    RecyclerView stepsRecyclerView;

    @BindView(R.id.fragment_recipeDetail_tablet)
    View recipeDetailTabletFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_recipe);

        ButterKnife.bind(this);

        name = getIntent().getStringExtra("name");
        ingredients = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("ingredients");
        steps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("steps");


        // Setup StepAdapter
        StepAdapter stepAdapter = new StepAdapter(steps, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        stepsRecyclerView.setAdapter(stepAdapter);
        stepsRecyclerView.setLayoutManager(linearLayoutManager);

        // Set ActionBar Title
        getSupportActionBar().setTitle(name);
    }

    public void showIngredients(View v) {

        if (recipeDetailTabletFragment != null) {

            IngredientsDetailFragment ingredientsDetailFragment = new IngredientsDetailFragment(ingredients);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tabletRight, ingredientsDetailFragment).commit();
        }

        else {
            Intent intent = new Intent(this, IngredientsDetailActivity.class);
            intent.putExtra("ingredients", ingredients);
            intent.putExtra("name", name);

            startActivity(intent);
        }

    }

    @Override
    public void onStepClick(int position) {

        String stepDesc = steps.get(position).get(RecipeJsonParser.STEP_DESC);


        if (recipeDetailTabletFragment != null) {

            StepFragment stepFragment = new StepFragment(stepDesc);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tabletRight, stepFragment).commit();



        }


        else {
            // Get current Step info

            Intent intent = new Intent(this, StepActivity.class);

            intent.putExtra("stepDesc", stepDesc);
            intent.putExtra("name", name);

            startActivity(intent);
        }



    }
}
