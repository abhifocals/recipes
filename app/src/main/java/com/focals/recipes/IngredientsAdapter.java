package com.focals.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IngredientsAdapter extends ArrayAdapter<HashMap<String, String>> {

    private ArrayList<HashMap<String, String>> ingredientsList;
    private int layoutRes;

    public IngredientsAdapter(@NonNull Context context, int layoutRes, @NonNull ArrayList<HashMap<String, String>> ingredients) {
        super(context, layoutRes, ingredients);
        ingredientsList = ingredients;
        this.layoutRes = layoutRes;
    }

    static class ViewHolder {
        TextView ingredientTextView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());

        convertView = inflater.inflate(layoutRes, parent, false);
        holder.ingredientTextView = convertView.findViewById(R.id.tv_ingredient);

        // Set name of Ingredient in TextView
        HashMap<String, String> currentIngredient = ingredientsList.get(position);
        holder.ingredientTextView.setText(currentIngredient.get(RecipeJsonParser.INGREDIENT));

        return convertView;
    }
}
