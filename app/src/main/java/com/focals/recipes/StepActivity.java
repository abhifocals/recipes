package com.focals.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;

public class StepActivity extends AppCompatActivity {

    TextView stepDescriptionTextView;
    SimpleExoPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Intent intent = getIntent();

        stepDescriptionTextView = findViewById(R.id.tv_stepDesc);

        stepDescriptionTextView.setText(intent.getStringExtra("stepDesc"));

        // Set ActionBar Title
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

    }
}
