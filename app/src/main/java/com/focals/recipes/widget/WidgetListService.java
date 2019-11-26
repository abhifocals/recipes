package com.focals.recipes.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WidgetListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetListServiceFactory(this);
    }
}

class WidgetListServiceFactory implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    ArrayList<String> ingredientsList = new ArrayList<>();

    public WidgetListServiceFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    /**
     * Updates data in the widget if a new recipe is clicked.
     */

    @Override
    public void onDataSetChanged() {
        if (RecipeWidgetProvider.RECIPE_POSITION >= 0) {
            ingredientsList = RecipeJsonParser.getRecipes().get(RecipeWidgetProvider.RECIPE_POSITION).getIngredients();
        }

        Log.d(this.getClass().getSimpleName(), String.valueOf(RecipeWidgetProvider.RECIPE_POSITION));
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientsList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.appwidget_text, ingredientsList.get(position));

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
