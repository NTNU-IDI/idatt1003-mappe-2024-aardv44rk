package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.util.List;
import java.util.Map;
import java.util.Random;


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
    if (recipes.containsKey(recipe.getName())) {
      throw new IllegalStateException("Recipe of same name already in cookbook!");
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
    ArgumentValidator.validateMapContainsKey(recipes, name, "Recipe not found");

    recipes.remove(name);
  }

  /**
   * Finds all recipes that are makeable in a cookbook based on Ingredients in a FoodStorage
   * <code>fs</code>.
   *
   * @param fs FoodStorage where Ingredients are stored
   * @return List consisting of all recipes that are makeable
   */
  public List<Recipe> getMakeableRecipes(FoodStorage fs) {
    return this.recipes.values().stream().filter(recipe -> recipe.isMakeableRecipe(fs)).toList();
  }

  /**
   * Recommends recipes based on the makeable recipes in a cookbook and the ingredients
   * in a FoodStorage <code>fs</code>.
   *
   * @param fs FoodStorage where Ingredients are stored
   * @return a list of recipes that are recommended to make
   */
  public Recipe recommendRecipe(FoodStorage fs) {
    List<Recipe> makeableRecipes = getMakeableRecipes(fs);

    Random random = new Random();
    int randomIndex = random.nextInt(makeableRecipes.size());

    return makeableRecipes.get(randomIndex);
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