package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;

public class Cookbook {
    private final List<Recipe> cookbook;
    private final FoodStorage foodStorage = new FoodStorage();

    public Cookbook() {
        cookbook = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        cookbook.add(recipe);
    }

    // TODO: decide between returning list of all "makeable" recipes, or recommending 2-3 of them
    // could also recommend a recipe if we have 9/10 of the ingredients needed ? 
    // also make it so 
    public ArrayList<Recipe> recommendRecipes() { // wip needs fixing
        ArrayList<Ingredient> availableIngredients = new ArrayList<>();
        ArrayList<Recipe> recommendedRecipes = new ArrayList<>();
        for (Recipe recipe : cookbook) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (foodStorage.getIngredientList().containsKey(ingredient.getName())) {
                    availableIngredients.add(ingredient);
                }
            }
            if (availableIngredients.containsAll(recipe.getIngredients())) {
                recommendedRecipes.add(recipe);
            }
        }
        return recommendedRecipes;
    }

    // getter

    public List<Recipe> getCookbook() {
        return cookbook;
    }
}