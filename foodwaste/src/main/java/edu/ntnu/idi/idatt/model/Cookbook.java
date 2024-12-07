package edu.ntnu.idi.idatt.model;

import java.util.List;
import java.util.Map;


/**
 * Cookbook class manages Recipes and stores them in a list.
 *
 * @author @aardv44rk
 * @since November 27th 2024
 * @version 2.0
 */
public class Cookbook {
  private final Map<String, Recipe> recipes;
  
  public Cookbook() {
    recipes = new LowerCaseMap<>();
  }

  /**
   * Adds a Recipe <code>recipe</code> to the cookbook.
   *
   * @param recipe Recipe to be added
   */
  public void addRecipe(Recipe recipe) {  
    recipes.put(recipe.getName(), recipe);
  }

  /**
   * Removes a Recipe <code>recipe</code> from the cookbook.
   *
   * @param name String representing name of ingredient to be removed
   * @throws IllegalStateException if recipe not present
   */
  public void removeRecipe(String name) throws IllegalStateException {
    recipes.remove(name);
  }

  /**
   * Lists names of all recipes in the cookbook.
   *
   * @return List of all keys in <code>recipes</code>
   */
  public List<String> recipeNamesToList() {
    return this.recipes.keySet().stream().toList();
  }

  public Map<String, Recipe> getRecipes() {
    return recipes;
  }
}