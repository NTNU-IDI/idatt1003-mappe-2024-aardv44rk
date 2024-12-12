package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents the controller for the cookbook.
 * It is responsible for handling the logic for the cookbook.
 */
public class CookbookController {
  private final Cookbook cookbook;
  private final StorageController storageController;

  public CookbookController(Cookbook cookbook, StorageController storageController) {
    this.cookbook = cookbook;
    this.storageController = storageController;
  }

  /**
   * Adds a recipe to the cookbook.
   *
   * @param recipe the recipe to add
   */
  public void addRecipe(Recipe recipe) {
    ArgumentValidator.isValidObject(recipe, "Recipe cannot be null");
    if (cookbook.getRecipes().containsKey(recipe.getName().toLowerCase())) {
      throw new IllegalStateException("Recipe already exists in cookbook");
    }

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
        .filter(recipe -> isMakeableRecipe(recipe))
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
        .filter(recipe -> containsEnoughIngredients(recipe, 0.8))
        .forEach(recommendations::add);

    return recommendations;
  }

  /**
   * Checks if the users storage contains enough ingredients to make Recipe {@code recipe} based
   * on a given threshold. If an ingredient in the storage is expired, it is not counted as
   * available.
   *
   * @param recipe the recipe to check
   * @param threshold the threshold for the amount of ingredients needed
   * @return true if the recipe contains enough ingredients, false otherwise
   */
  public boolean containsEnoughIngredients(Recipe recipe, double threshold) {
    int availableIngredients = (int) recipe.getIngredientMap().values().stream()
        .filter(ingredient -> storageController.containsIngredient(ingredient.getName(),
                ingredient.getAmount()) && !ingredient.getExpiryDate().isBefore(LocalDate.now()))
        .count();
    return (double) availableIngredients / recipe.getIngredientMap().size() >= threshold;
  }

  /**
   * Compares the ingredients in a recipe to the ingredients in a storage.
   * Returns true or false based on the result.
   *
   * @param recipe Recipe to check for
   */
  public boolean isMakeableRecipe(Recipe recipe) {
    ArgumentValidator.isValidObject(recipe, "Recipe cannot be null");
    Map<String, Ingredient> ingredients = recipe.getIngredientMap();
    return ingredients.values().stream()
        .allMatch(ingredient -> 
        storageController.containsIngredient(ingredient.getName(), ingredient.getAmount()));
  }

  public Map<String, Recipe> getRecipes() {
    return new TreeMap<>(cookbook.getRecipes());
  }

  /**
   * Method that prints the name and description of a <code>Recipe</code> object, specifically
   * for the CookbookMenu#viewCookbook() method. If the recipe is makeable, the name will be
   * printed in green.
   *
   * @return a string consisting of the name and description of a recipe
   */
  public String recipeToMapString(Recipe recipe) {
    ArgumentValidator.isValidObject(recipe, "Recipe cannot be null");
    final String green = "\u001B[32m";
    final String reset = "\u001B[0m";
    if (isMakeableRecipe(recipe)) {
      return String.format(
        "%-20s %-50s", green + recipe.getName() + reset + " (Makeable)", recipe.getDescription()
      );
    }
    return String.format("%-20s %-50s", recipe.getName(), recipe.getDescription());
  }
}
