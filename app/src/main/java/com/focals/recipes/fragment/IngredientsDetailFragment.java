package com.focals.recipes.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.focals.recipes.R;
import com.focals.recipes.adapter.IngredientsAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsDetailFragment extends Fragment {

    private ArrayList<HashMap<String, String>> ingredients;

    @BindView(R.id.lv_ingredients)
    ListView ingredientsListView;

    public IngredientsDetailFragment() {
        // Required empty public constructor
    }

    public IngredientsDetailFragment(ArrayList<HashMap<String, String>> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredients_detail, container, false);

        ButterKnife.bind(this, view);

        // Setup IngredientAdapter
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getContext(), R.layout.activity_detail_ingredients_singleingredient, ingredients);
        ingredientsListView.setDivider(null);
        ingredientsListView.setAdapter(ingredientsAdapter);


        // Inflate the layout for this fragment
        return view;
    }

}
