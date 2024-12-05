package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CookBookTest {
  private Cookbook cookbook;
  private Recipe recipe;
  private String name;
  private String otherName;
  private double amount;
  private String oatUnit;
  private String otherUnit;
  private Map<String, Ingredient> ingredientMap;

  @BeforeEach
  void testInit() {
    cookbook = new Cookbook();
    ingredientMap = new LowerCaseMap<>();
    name = "oats";
    otherName = "water";
    amount = 100;
    oatUnit = "g";
    otherUnit = "mL";
    Ingredient oats = new Ingredient(name, amount, oatUnit);
    Ingredient water = new Ingredient(otherName, amount, otherUnit);
    ingredientMap.put("oats", oats);
    ingredientMap.put("water", water);
    recipe = new Recipe(
        "Oatmeal",
        "A mediocre meal consisting of oats and water.",
        "Boil water, add oats, bon apetit",
        1.0,
        ingredientMap);
  }

  @Test
  void testAddRecipe() {
    cookbook.addRecipe(recipe);
    assertTrue(cookbook.getRecipes().containsValue(recipe),
        "Should contain recipe");

    assertTrue(cookbook.getRecipes().containsKey(recipe.getName().toLowerCase()),
        "Key should match recipe name to lowercase");
  }

  @Test
  void testRemoveRecipe() {
    cookbook.addRecipe(recipe);
    assertTrue(cookbook.getRecipes().containsValue(recipe), "Should contain recipe");
    cookbook.removeRecipe(recipe.getName());
    assertFalse(cookbook.getRecipes().containsValue(recipe), "Should not contain recipe");
  }

  @Test
  void testRecipeNamesToList() {
    cookbook.addRecipe(recipe);
    cookbook.addRecipe(
        new Recipe(
            "Oatmeal 2",
            recipe.getDescription(),
            recipe.getInstruction(),
            recipe.getPortions(),
            recipe.getIngredientMap()));
    assertTrue(cookbook.recipeNamesToList().contains("oatmeal 2"),
        "Should contain recipe (lowercase name)");
    assertTrue(cookbook.recipeNamesToList().contains("oatmeal"),
        "Should contain recipe (lowercase name)");
  }

  @Test
  void testRecommendRecipes() {
    cookbook.addRecipe(recipe);
    cookbook.addRecipe(
        new Recipe(
            "Oatmeal 2",
            recipe.getDescription(),
            recipe.getInstruction(),
            recipe.getPortions(),
            recipe.getIngredientMap()));
    FoodStorage fs = new FoodStorage();
    fs.addIngredient(new Ingredient("oats", 10, LocalDate.now(), amount, oatUnit));
    fs.addIngredient(new Ingredient("water", 10, LocalDate.now(), amount, otherUnit));
    assertTrue(cookbook.getMakeableRecipes(fs).contains(recipe), "Recipe should be contained");
    assertEquals(2, cookbook.getMakeableRecipes(fs).size(), "Should contain 2 recipes");
  }

  @Test
  void testAddRecipeThrowsException() {
    cookbook.addRecipe(recipe);
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> cookbook.addRecipe(recipe),
        "Should throw exception for duplicate recipe");

    assertEquals("Recipe of same name already in cookbook!",
        e.getMessage(), "Messages should match");
  }

}
