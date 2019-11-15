package com.focals.recipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.focals.recipes.R;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class StepActivity extends AppCompatActivity {

    @BindView(R.id.tv_stepDesc)
    TextView stepDescriptionTextView;
    SimpleExoPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        ButterKnife.bind(this);

        Intent intent = getIntent();

//        stepDescriptionTextView.setText(intent.getStringExtra("stepDesc"));

        // Set ActionBar Title
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

    }
}
