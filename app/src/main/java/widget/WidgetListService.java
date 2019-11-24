package widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeJsonParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;

public class WidgetListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetListServiceFactory(this);
    }
}

class WidgetListServiceFactory implements RemoteViewsService.RemoteViewsFactory{

    Context context;


    public WidgetListServiceFactory(Context context) {
        this.context = context;


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
