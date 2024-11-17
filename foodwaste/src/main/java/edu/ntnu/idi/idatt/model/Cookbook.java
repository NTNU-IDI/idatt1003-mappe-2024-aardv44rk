package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Cookbook</code> class manages a list of recipes.
 *
 * <p>This class stores recipes in a list and provides methods to
 * add and recommend the user recipes.</p>
 */

public class Cookbook {
  private final List<Recipe> cookbook;
  private final FoodStorage foodStorage = new FoodStorage();

  public Cookbook() {
    cookbook = new ArrayList<>();
  }

  
  /**
   * Adds a recipe to the cookbook.
   * 
   * <p>The <code>addRecipe</code> method stores a <code>Recipe</code> in a <code>List</code>.</p>
   *
   * @param recipe The desired recipe to add.
   */
  public void addRecipe(Recipe recipe) {
    cookbook.add(recipe);
  }

  // TODO: decide between returning list of all "makeable" recipes, or
  // recommending 2-3 of them
  // could also recommend a recipe if we have 9/10 of the ingredients needed ?
  // also make it so it sorts after expirydate
  /**
   * Recommends the user recipes based on the ingredients they have available.
   *
   * <p>The <code>recommendRecipes</code> method searches through available
   * ingredients and returns a list of recipes that can be made with the
   * ingredients available to the user.
   *
   * @return <code>ArrayList&lt;Recipe&gt;</code> A list of recommended recipes.</p>
   */
  public ArrayList<Recipe> recommendRecipes() { // wip needs fixing
    ArrayList<Ingredient> availableIngredients = new ArrayList<>();
    ArrayList<Recipe> recommendedRecipes = new ArrayList<>();
    for (Recipe recipe : cookbook) {
      for (Ingredient ingredient : recipe.getIngredients()) {
        if (foodStorage.getIngredientList().containsKey(ingredient.getName())) {
          // TODO amount handling here
          availableIngredients.add(ingredient);
        }
      }
      if (availableIngredients.containsAll(recipe.getIngredients())) {
        recommendedRecipes.add(recipe);
      }
    }
    return recommendedRecipes;
  }

  public List<Recipe> getCookbook() {
    return cookbook;
  }
}