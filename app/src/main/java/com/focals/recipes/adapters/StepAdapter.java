package com.focals.recipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.focals.recipes.R;
import com.focals.recipes.utils.RecipeJsonParser;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepHolder> {

    private ArrayList<HashMap<String, String>> stepsList;
    public StepClickHandler clickHandler;

    public StepAdapter(ArrayList<HashMap<String, String>> stepsList, StepClickHandler clickHandler) {
        this.stepsList = stepsList;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public StepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_detail_recipe_singlestep, parent, false);

        StepHolder holder = new StepHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepHolder holder, int position) {

        HashMap<String, String> currentStep = stepsList.get(position);

        holder.stepTextView.setText(currentStep.get(RecipeJsonParser.STEP_SHORT_DESC));
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }


    // To pass click implementation to RecipeDetailActivity
    public interface StepClickHandler {
        void onStepClick(int position);
    }


    class StepHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_stepShortDescription)
        TextView stepTextView;

        public StepHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

            stepTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickHandler.onStepClick(getAdapterPosition());
        }
    }


}
