package com.focals.recipes.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.focals.recipes.R;

import java.util.Arrays;
import java.util.List;

public class WidgetListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        int recipePosition = intent.getIntExtra(getString(R.string.recipe_position), -1);

        return new WidgetListServiceFactory(this, recipePosition);
    }
}

class WidgetListServiceFactory implements RemoteViewsService.RemoteViewsFactory{

    Context context;
    private int recipePosition;


    public WidgetListServiceFactory(Context context, int recipePosition) {
        this.context = context;
        this.recipePosition = recipePosition;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public RemoteViews getViewAt(int position) {

      String[] ingredients = {"One", "Two", "Three"};
      List<String> ingredientsList = Arrays.asList(ingredients);

      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

      String text = ingredients[position];

      views.setTextViewText(R.id.appwidget_text, text);




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
