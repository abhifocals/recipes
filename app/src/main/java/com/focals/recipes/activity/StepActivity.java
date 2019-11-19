package com.focals.recipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeJsonParser;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.tv_stepDesc)
    TextView stepDescriptionTextView;
    SimpleExoPlayer player;
    int recipePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        recipePosition = getIntent().getIntExtra("recipePosition", -1);

        // Set ActionBar Title
        String recipeName = RecipeJsonParser.getRecipes().get(recipePosition).getName();
        getSupportActionBar().setTitle(recipeName);
    }

}
