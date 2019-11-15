package com.focals.recipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.ListView;

import com.focals.recipes.adapter.IngredientsAdapter;
import com.focals.recipes.R;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsDetailActivity extends AppCompatActivity {


    @BindView(R.id.lv_ingredients)
    ListView ingredientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_ingredients);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

    }
}
