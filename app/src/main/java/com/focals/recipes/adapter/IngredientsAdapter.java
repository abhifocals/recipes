package com.focals.recipes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends ArrayAdapter<HashMap<String, String>> {

    private ArrayList<HashMap<String, String>> ingredientsList;
    private int layoutRes;

    public IngredientsAdapter(@NonNull Context context, int layoutRes, @NonNull ArrayList<HashMap<String, String>> ingredients) {
        super(context, layoutRes, ingredients);
        ingredientsList = ingredients;
        this.layoutRes = layoutRes;
    }

    class ViewHolder {
        @BindView(R.id.tv_ingredient)
        TextView ingredientTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(layoutRes, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        // Set name of Ingredient in TextView
        HashMap<String, String> currentIngredient = ingredientsList.get(position);

        String ingredient = currentIngredient.get(RecipeJsonParser.INGREDIENT);
        String qty = currentIngredient.get(RecipeJsonParser.QUANTITY);
        String measure = currentIngredient.get(RecipeJsonParser.MEASURE);
        String ingredientWithQtyMeasure = qty + " " + measure + " " + ingredient;

        holder.ingredientTextView.setText(ingredientWithQtyMeasure);

        return convertView;
    }
}