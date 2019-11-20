package com.focals.recipes.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.focals.recipes.R;
import com.focals.recipes.adapter.IngredientsAdapter;
import com.focals.recipes.utils.RecipeConstants;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

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

    /**
     * Used  by Tablet for explicit Fragment creation, since IngredientActivity is not started.
     *
     * @param ingredients
     */
    public IngredientsDetailFragment(ArrayList<HashMap<String, String>> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredients_detail, container, false);

        // Used for Phone (since Tablet explicitly creates Fragment with ingredient list)
        if (ingredients == null) {
            ingredients = (ArrayList<HashMap<String, String>>) getActivity().getIntent().getSerializableExtra(RecipeConstants.INGREDIENTS_LIST);
        }

        // Binding views with Butterknife
        ButterKnife.bind(this, view);

        // Setup IngredientAdapter
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getContext(), R.layout.activity_detail_ingredients_singleingredient, ingredients);
        ingredientsListView.setDivider(null);
        ingredientsListView.setAdapter(ingredientsAdapter);

        return view;
    }
}
