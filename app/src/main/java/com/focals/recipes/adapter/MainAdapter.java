package com.focals.recipes.adapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.focals.recipes.R;
        import com.focals.recipes.utils.Recipe;

        import java.util.ArrayList;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import butterknife.BindView;
        import butterknife.ButterKnife;

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
        @BindView(R.id.recipeImage)
        ImageView recipeImageView;

        @BindView(R.id.recipeTitle)
        TextView recipeTitleTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recipe recipe = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResourceId, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        // Setting View Data
        holder.recipeTitleTextView.setText(recipe.getName());

        if (recipe.getName().equals(context.getResources().getString(R.string.nutellaPie))) {
            holder.recipeImageView.setImageResource(R.drawable.nutella_pie);
        } else if (recipe.getName().equals(context.getResources().getString(R.string.brownies))) {
            holder.recipeImageView.setImageResource(R.drawable.brownies);
        } else if (recipe.getName().equals(context.getResources().getString(R.string.cheesecake))) {
            holder.recipeImageView.setImageResource(R.drawable.cheesecake);
        } else if (recipe.getName().equals(context.getResources().getString(R.string.yellowCake))) {
            holder.recipeImageView.setImageResource(R.drawable.yellow_cake);
        }

        return convertView;
    }
}
