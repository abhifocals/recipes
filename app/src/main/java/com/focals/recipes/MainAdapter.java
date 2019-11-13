package com.focals.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Recipe> recipelist;

    public MainAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Recipe> recipelist) {
        super(context, resource, recipelist);

        this.context = context;
        this.layoutResourceId = resource;
        this.recipelist = recipelist;
    }

    static class ViewHolder {
        ImageView recipeImageView;
        TextView recipeTitleTextView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recipe recipe = getItem(position);
        ViewHolder holder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResourceId, parent, false);

        // Finding Views
        holder.recipeImageView = (ImageView) convertView.findViewById(R.id.recipeImage);
        holder.recipeTitleTextView = (TextView) convertView.findViewById(R.id.recipeTitle);

        // Setting View Data
        holder.recipeTitleTextView.setText(recipe.getName());


        // Extract into method
        switch (recipe.getName()) {

            case "Nutelle Pie":
                holder.recipeImageView.setImageResource(R.drawable.nutella_pie);
                break;

            case "Brownies":
                holder.recipeImageView.setImageResource(R.drawable.brownies);
                break;

            case "Cheesecake":
                holder.recipeImageView.setImageResource(R.drawable.cheesecake);
                break;

            case "Yellow Cake":
                holder.recipeImageView.setImageResource(R.drawable.yellow_cake);
                break;


        }

        return convertView;
    }
}
