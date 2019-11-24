package widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.focals.recipes.R;
import com.focals.recipes.activity.MainActivity;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

    public static final String RECIPE_INTENT = "com.focals.recipes.recipe_intent";
    private int recipePosition = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(RECIPE_INTENT)) {
            int position = intent.getIntExtra(context.getString(R.string.recipe_position), -1);
            recipePosition = position;






        }
    }

    private void updateWidgetsWithRecipeIngredients(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds,  int recipePosition) {


        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);

        ComponentName componentName = new ComponentName(context.getPackageName(), );



//        int[] widgetIds = widgetManager.getAppWidgetIds(new ComponentName(context.getPackageName(),RecipeWidgetProvider.class));





    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        // Set  ListView
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_list);
        Intent listIntent = new Intent(context, WidgetListService.class);
        listIntent.putExtra(context.getString(R.string.recipe_position), -1);
        views.setRemoteAdapter(R.id.listView_Widget, listIntent);






        // Add PendingIntent to start MainActivity
        Intent launchIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

