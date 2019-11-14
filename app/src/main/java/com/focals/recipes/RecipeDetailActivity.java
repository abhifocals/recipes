package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeDetailActivity extends AppCompatActivity implements StepAdapter.StepClickHandler {

    private String name;
    private ArrayList<HashMap<String, String>> ingredients;
    private ArrayList<HashMap<String, String>> steps;

    @BindView(R.id.rv_steps)
    RecyclerView stepsRecyclerView;



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

        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("ingredients", ingredients);
        intent.putExtra("name", name);

        startActivity(intent);
    }

    @Override
    public void onStepClick(int position) {

        // Get current Step info

        String stepDesc = steps.get(position).get(RecipeJsonParser.STEP_DESC);

        Intent intent = new Intent(this, StepActivity.class);

        intent.putExtra("stepDesc", stepDesc);
        intent.putExtra("name", name);

        startActivity(intent);
    }
}
