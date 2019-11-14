package com.focals.recipes.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private String id;
    private String name;
    private ArrayList<HashMap<String, String>> ingredients;
    private ArrayList<HashMap<String, String>> steps;
    private String servings;
    private String imageUrl;

    public Recipe(String id, String name, ArrayList<HashMap<String, String>> ingredients, ArrayList<HashMap<String, String>> steps, String service, String imageUrl) {
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

    public ArrayList<HashMap<String, String>> getIngredients() {
        return ingredients;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<HashMap<String, String>> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(ArrayList<HashMap<String, String>> steps) {
        this.steps = steps;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
