package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class responsible for testing all operations relating to Recipe objects.
 */
class RecipeTest {


  private String name;
  private String description;
  private String instruction;
  private Map<String, Ingredient> ingredientMap;
  private double amount;
  private String oatUnit;
  private String otherUnit;
  private double portions;
  private Recipe recipe;
  Ingredient oats;
  Ingredient water;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    name = "Oatmeal";
    description = "A mediocre meal consisting of oats and water.";
    instruction = "Boil water, add oats, bon apetit";
    ingredientMap = new LowerCaseMap<>();
    amount = 100;
    oatUnit = "g";
    otherUnit = "mL";
    oats = new Ingredient("oats", amount, oatUnit);
    water = new Ingredient("water", amount, otherUnit);
    ingredientMap.put("Oats", oats);
    ingredientMap.put("Water", water);
    portions = 1.0;
    recipe = new Recipe(name, description, instruction, portions, ingredientMap);
  }

  @Test
  void testConstructor() {
    assertEquals(name, recipe.getName(), "Name fields should match");
    assertEquals(description, recipe.getDescription(), "Description fields should match");
    assertEquals(instruction, recipe.getInstruction(), "Instruction fields should match");
    assertEquals(ingredientMap, recipe.getIngredientMap(), "Ingredient lists should match");
    assertEquals(portions, recipe.getPortions(), "Portion integers should match");
  }

  @Test
  void testSetName() {
    String expected = "Oatmeal Deluxe";
    recipe.setName(expected);
    assertEquals(expected, recipe.getName());
  }

  @Test
  void testSetDescription() {
    String expected = "Same as oatmeal... but this time with milk!";
    recipe.setDescription(expected);
    assertEquals(expected, recipe.getDescription());
  }

  @Test
  void testSetInstruction() {
    String expected = "Bring the milk to pasteurizing temperature, add oats, voila!";
    recipe.setInstruction(expected);
    assertEquals(expected, recipe.getInstruction());
  }

  @Test
  void testSetIngredientMap() {
    Map<String, Ingredient> expected = new HashMap<>();
    String ingredientName = "Milk";
    double amount = 100;
    String unit = "mL";
    Ingredient milk = new Ingredient(ingredientName, amount, unit);
    expected.put(ingredientName, milk);
    expected.put("Oats", oats);
    recipe.setIngredientMap(expected);
    assertEquals(expected, recipe.getIngredientMap());
  }

  @Test
  void testSetPortions() {
    double expected = 2;
    recipe.setPortions(expected);
    assertEquals(expected, recipe.getPortions());
  }

  @Test
  void testPrintRecipe() {
    assertEquals("""
        ----------------------------------------
        Oatmeal
        A mediocre meal consisting of oats and water.
        Ingredients:
        * oats 100.0 g
        * water 100.0 mL

        Step by step:
        Boil water, add oats, bon apetit""",
        recipe.recipeToString(), "Should be equal");
  }

  // Negative tests
  @Test
  void testSetNameNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setName(null),
        "Should throw IllegalArgumentException if name null");
    assertEquals("Name field cannot be empty!", e.getMessage());
  }

  @Test
  void testSetDescriptionNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setDescription(null),
        "Should throw IllegalArgumentException if null is passed");
    assertEquals("Description field cannot be empty!", e.getMessage());
  }

  @Test
  void testSetInstructionNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setInstruction(null),
        "Should throw IllegalArgumentException if null is passed");
    assertEquals("Instruction field cannot be empty!", e.getMessage());
  }

  @Test
  void testSetIngredientsNull() {
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> recipe.setIngredientMap(null),
        "Should throw IllegalArgumentException if null is passed");
    assertEquals("Recipe cannot have zero ingredients!", e.getMessage());
  }

  @Test
  void testSetPortionsZero() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setPortions(0),
        "Should throw IllegalArgumentException if zero is passed");
    assertEquals("Recipe cannot have zero or negative amount of portions!", e.getMessage());
  }

  @Test
  void testSetNameEmpty() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setName(""),
        "Should throw IllegalArgumentException if ");
    assertEquals("Name field cannot be empty!", e.getMessage());
  }

  @Test
  void testSetDescriptionEmpty() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setDescription(""),
        "Should throw IllegalArgumentException if null is passed");
    assertEquals("Description field cannot be empty!", e.getMessage());
  }

  @Test
  void testSetInstructionEmpty() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setInstruction(""),
        "Should throw IllegalArgumentException if null is passed");
    assertEquals("Instruction field cannot be empty!", e.getMessage());
  }

  @Test
  void testSetIngredientMapEmpty() {
    Map<String, Ingredient> emptyMap = new HashMap<>();
    IllegalStateException e = assertThrows(IllegalStateException.class,
        () -> recipe.setIngredientMap(emptyMap),
        "Should throw IllegalArgumentException if empty map is passed");
    assertEquals("Recipe cannot have zero ingredients!", e.getMessage());
  }

  @Test
  void testSetPortionsNegative() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> recipe.setPortions(-1),
        "Should throw IllegalArgumentException if negative number is passed");
    assertEquals("Recipe cannot have zero or negative amount of portions!", e.getMessage(),
        "Messages should match");
  }
}