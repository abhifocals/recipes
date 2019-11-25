package com.focals.recipes.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeJsonParser;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    public static final String RECIPE_INTENT = "com.focals.recipes.recipe_intent";
    public static int RECIPE_POSITION = -1;
    static Intent listIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(RECIPE_INTENT)) {
            RECIPE_POSITION = intent.getIntExtra(context.getString(R.string.recipe_position), -1);

            AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context.getPackageName(),"com.focals.recipes.widget.RecipeWidgetProvider" );
            int[] ids = widgetManager.getAppWidgetIds(componentName);

            widgetManager.notifyAppWidgetViewDataChanged(ids, R.id.listView_Widget);
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Set  ListView
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list);
        listIntent = new Intent(context, WidgetListService.class);
        views.setRemoteAdapter(R.id.listView_Widget, listIntent);

        views.setTextViewText(R.id.widget_recipe_title, RecipeJsonParser.getRecipes().get(RECIPE_POSITION).getName());

        // Instruct the com.focals.recipes.widget manager to update the com.focals.recipes.widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first com.focals.recipes.widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last com.focals.recipes.widget is disabled
    }
}

