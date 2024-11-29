package edu.ntnu.idi.idatt.model;

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
  private Map<String, Recipe> recipes;

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
}