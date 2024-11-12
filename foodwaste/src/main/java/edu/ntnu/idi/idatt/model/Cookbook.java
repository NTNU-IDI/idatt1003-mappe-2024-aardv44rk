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

    public Recipe recommendRecipe() { // wip needs fixing
        Recipe recommendedRecipe = null;
        for (Recipe recipe : cookbook) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                // might be issues with ingredientList being a HashMap with ingredient values? 
                // TODO: figure out algorithm here, if not need to change from hm to arraylist etc IMPORTANT
            }
        }
        return recommendedRecipe;
    }
}
