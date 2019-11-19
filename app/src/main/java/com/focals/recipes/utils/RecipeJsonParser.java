package com.focals.recipes.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeJsonParser {

    public static final String QUANTITY = "quantity";
    public static final String MEASURE = "measure";
    public static final String INGREDIENT = "ingredient";

    public static final String STEP_ID = "id";
    public static final String STEP_SHORT_DESC = "shortDescription";
    public static final String STEP_DESC = "description";
    public static final String STEP_VIDEO = "videoURL";
    public static final String STEP_THUMBNAIL = "thumbnailURL";

    private static ArrayList<Recipe> recipes = new ArrayList<>();

    public static ArrayList<Recipe> getRecipes(Context context) {

        try {
            JSONArray recipeArray = new JSONArray(readJsonFromAssets(context));


            for (int i = 0; i < recipeArray.length(); i++) {

                JSONObject object = recipeArray.getJSONObject(i);

                String id = object.getString("id");
                String name = object.getString("name");
                String servings = object.getString("servings");
                String imageUrl = object.getString("image");


                // Parse Ingredients

                ArrayList<HashMap<String, String>> ingredientsList = new ArrayList<>();

                JSONArray ingredientsArray = object.getJSONArray("ingredients");

                for (int j = 0; j < ingredientsArray.length(); j++) {

                    JSONObject ingredientObject = ingredientsArray.getJSONObject(j);
                    HashMap<String, String> singleIngredient = new HashMap<>();

                    singleIngredient.put(QUANTITY, ingredientObject.getString(QUANTITY).toLowerCase());
                    singleIngredient.put(MEASURE, ingredientObject.getString(MEASURE).toLowerCase());
                    singleIngredient.put(INGREDIENT, ingredientObject.getString(INGREDIENT).toLowerCase());

                    ingredientsList.add(singleIngredient);
                }


                // Parse Steps

                ArrayList<HashMap<String, String>> stepsList = new ArrayList<>();
                JSONArray stepsArray = object.getJSONArray("steps");

                for (int j = 0; j < stepsArray.length(); j++) {

                    JSONObject stepsObject = stepsArray.getJSONObject(j);
                    HashMap<String, String> singleStep = new HashMap<>();

                    singleStep.put(STEP_ID, stepsObject.getString(STEP_ID));
                    singleStep.put(STEP_SHORT_DESC, stepsObject.getString(STEP_SHORT_DESC));
                    singleStep.put(STEP_DESC, stepsObject.getString(STEP_DESC));
                    singleStep.put(STEP_VIDEO, stepsObject.getString(STEP_VIDEO));
                    singleStep.put(STEP_THUMBNAIL, stepsObject.getString(STEP_THUMBNAIL));

                    stepsList.add(singleStep);
                }

                Recipe recipe = new Recipe(id, name, ingredientsList, stepsList, servings, imageUrl);

                recipes.add(recipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipes;

    }

    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    private static String readJsonFromAssets(Context context) {

        String jsonString = null;

        try {
            InputStream is = context.getAssets().open("recipes.json");
            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();

            jsonString = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

}
