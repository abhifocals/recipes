package com.focals.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Recipe> recipelist;

    public RecipeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Recipe> recipelist) {
        super(context, resource, recipelist);

        this.context = context;
        this.layoutResourceId = resource;
        this.recipelist = recipelist;
    }

    static class ViewHolder {
        ImageView recipeImage;
        TextView recipeTitle;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        Recipe recipe = getItem(position);

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.recipeImage = (ImageView) convertView.findViewById(R.id.recipeImage);
            holder.recipeTitle = (TextView) convertView.findViewById(R.id.recipeTitle);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag(); // TODO remove and see what happens. 15m.
        }

        holder.recipeImage.setImageResource(R.drawable.nutella_pie);
        holder.recipeTitle.setText(recipe.getName());

        return convertView;
    }
}
