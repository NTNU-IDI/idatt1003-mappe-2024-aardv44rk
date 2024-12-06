package edu.ntnu.idi.idatt.controller;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the controller for the cookbook.
 * It is responsible for handling the logic for the cookbook.
 */
public class CookbookController {
  private final Cookbook cookbook;
  private final FoodStorage fs;

  public CookbookController(Cookbook cookbook, FoodStorage fs) {
    this.cookbook = cookbook;
    this.fs = fs;
  }

  public void addRecipe(Recipe recipe) {
    // TODO Validation
    cookbook.addRecipe(recipe);
  }

  public void removeRecipe(String name) {
    ArgumentValidator.isValidString(name, "Name cannot be null or empty");
    cookbook.removeRecipe(name);
  }

  /**
   * Finds all recipes that are makeable in a cookbook based on Ingredients in a FoodStorage
   * <code>fs</code>.
   *
   * @return List consisting of all recipes that are makeable
   */
  public List<Recipe> getMakeableRecipes() {
    return cookbook.getRecipes().values().stream()
        .filter(recipe -> recipe.isMakeableRecipe(fs))
        .toList();    
  }

  /**
   * Recommends recipes based on the ingredients available in a FoodStorage <code>fs</code>.
   *
   * @return List consisting of all recipes with more than 80% of the ingredients available
   */
  public List<Recipe> recommendRecipes() {
    List<Recipe> recommendations = new ArrayList<>();

    cookbook.getRecipes().values().stream()
        .filter(recipe -> recipe.isMakeableRecipe(fs))
        .forEach(recommendations::add);

    return recommendations;
  }

  /**
   * Checks if a recipe contains enough ingredients to be makeable based on a given 
   * threshold.
   *
   * @param recipe the recipe to check
   * @param threshold the threshold for the amount of ingredients needed
   * @return true if the recipe contains enough ingredients, false otherwise
   */
  public boolean containsEnoughIngredients(Recipe recipe, double threshold) {
    int availableIngredients = (int) recipe.getIngredientMap().values().stream()
        .filter(ingredient -> fs.containsIngredient(ingredient.getName(), ingredient.getAmount()))
        .count();
    return (double) availableIngredients / recipe.getIngredientMap().size() >= threshold;
  }

  
}
