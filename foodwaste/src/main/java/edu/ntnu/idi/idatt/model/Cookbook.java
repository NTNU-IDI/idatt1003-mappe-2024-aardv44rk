package edu.ntnu.idi.idatt.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Cookbook class manages Recipes and stores them in a list.
 *
 * @author @aardv44rk
 * @since November 27th 2024
 * @version 1.1
 */
public class Cookbook {
  private final Map<String, Recipe> recipes;

  public Cookbook() {
    recipes = new TreeMap<>();
  }

  /**
   * Adds a Recipe <code>recipe</code> to the cookbook.
   *
   * @param recipe Recipe to be added
   */
  public void addRecipe(Recipe recipe) {
    if (recipes.containsKey(recipe.getName())) {
      throw new IllegalStateException("Recipe already in cookbook!");
    }

    recipes.put(recipe.getName(), recipe);
  }

  /**
   * Removes a Recipe <code>recipe</code> from the cookbook.
   *
   * @param name String representing name of ingredient to be removed
   * @throws IllegalStateException if recipe not present
   */
  public void removeRecipe(String name) throws IllegalStateException {
    if (!recipes.containsKey(name)) {
      throw new IllegalStateException("Recipe not in cookbook!");
    }

    recipes.remove(name);
  }

  /**
   * Finds all recipes that are makeable in a cookbook based on Ingredients in a FoodStorage
   * <code>fs</code>.
   *
   * @param fs FoodStorage to be compared with.
   * @return List consisting of all recipes that are makeable
   */
  public List<Recipe> recommendRecipes(FoodStorage fs) {
    return this.recipes.values().stream().filter(recipe -> recipe.isMakeableRecipe(fs)).toList();
  }

  /**
   * Lists names of all recipes in the cookbook.
   *
   * @return List of all keys in <code>recipes</code>
   */
  public List<String> listRecipes() {
    return this.recipes.keySet().stream().toList();
  }
}