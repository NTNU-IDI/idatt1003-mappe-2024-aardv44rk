package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.controllers.CookbookController;
import edu.ntnu.idi.idatt.controllers.StorageController;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.model.LowerCaseMap;
import edu.ntnu.idi.idatt.model.Recipe;
import java.util.Map;

/**
 * Class responsible for initializing dummy values for testing purposes.
 */
public class DummyValues {

  private DummyValues() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Initializes dummy values for testing purposes.
   *
   * @param storageController  the storage controller
   * @param cookbookController the cookbook controller
   */
  public static void initializeDummyValues(
      StorageController storageController,
      CookbookController cookbookController) {
    storageController.addIngredient(
        new Ingredient("Egg", 20, DateUtil.parseDate("12-12-2024"), 6, "pcs"));
    storageController.addIngredient(
        new Ingredient("Milk", 15, DateUtil.parseDate("20-12-2024"), 1000, "ml"));
    storageController.addIngredient(
        new Ingredient("Flour", 15, DateUtil.parseDate("12-06-2025"), 1000, "g"));

    Map<String, Ingredient> ingredients = new LowerCaseMap<>();
    ingredients.put("Egg", new Ingredient("Egg", 6, "pcs"));
    ingredients.put("Milk", new Ingredient("Milk", 1000, "ml"));
    ingredients.put("Flour", new Ingredient("Flour", 500, "g"));

    cookbookController.addRecipe(
        new Recipe("Pancakes", "Delicious panckas", "Mix the ingredients, serve", 4.0, ingredients)
    );
  }
}
