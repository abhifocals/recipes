package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeDetailActivity extends AppCompatActivity {

    private String name;
    private ArrayList<HashMap<String, String>> ingredients;
    private ArrayList<HashMap<String, String>> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_recipe);

        name = getIntent().getStringExtra("name");
        ingredients = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("ingredients");
        steps = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("steps");


        // Setup StepAdapter
        StepAdapter stepAdapter = new StepAdapter(steps);
        RecyclerView stepsRecyclerView = findViewById(R.id.rv_steps);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);

        stepsRecyclerView.setAdapter(stepAdapter);
        stepsRecyclerView.setLayoutManager(gridLayoutManager);

        stepsRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open StepActivity. Or do it in the Adapter?
            }
        });


    }

    public void showIngredients(View v) {

        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("ingredients", ingredients);

        startActivity(intent);
    }
}
