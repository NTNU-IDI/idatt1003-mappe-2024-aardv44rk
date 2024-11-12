package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;

public class Cookbook {
    private List<Recipe> cookbook;

    public Cookbook() {
        cookbook = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        cookbook.add(recipe);
    }

    public Recipe recommendRecipe() { // wip needs fixing
        Recipe recommendedRecipe = null;

        return recommendedRecipe;
    }
}
