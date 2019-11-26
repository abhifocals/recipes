package com.focals.recipes.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private String id;
    private String name;
    private final ArrayList<String> ingredients;
    private final ArrayList<HashMap<String, String>> steps;
    private final String servings;
    private final String imageUrl;

    public Recipe(String id, String name, ArrayList<String> ingredients, ArrayList<HashMap<String, String>> steps, String service, String imageUrl) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = service;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<HashMap<String, String>> getSteps() {
        return steps;
    }

    public String getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
