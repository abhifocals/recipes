package com.focals.recipes.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.focals.recipes.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * This activity is started only for Phone.
 * For Tablet, IngredientDetailFragment is created.
 */

public class IngredientsDetailActivity extends AppCompatActivity {

    @BindView(R.id.lv_ingredients)
    ListView ingredientsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ingredients);

        // Binding views with Butterknife
        ButterKnife.bind(this);

        // Setting ActionBar title
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
    }
}
