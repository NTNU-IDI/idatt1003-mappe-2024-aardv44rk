package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
  void testConstructorNullName() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(null, description, instruction, portions, ingredientMap),
                "Should throw exception if name is null");
    assertEquals("Name cannot be empty!", e.getMessage(), "Should throw correct exception");
  }

  @Test
  void testConstructorEmptyName() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Recipe("", description, instruction, portions, ingredientMap),
                "Should throw exception if name is empty");
    assertEquals("Name cannot be empty!", e.getMessage(), "Should throw correct exception");
  }

  @Test
  void testConstructorNullDescription() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, null, instruction, portions, ingredientMap),
                "Should throw exception if description is null");
    assertEquals("Description cannot be empty!", e.getMessage(), "Should throw correct exception");
  }

  @Test
  void testConstructorEmptyDescription() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, "", instruction, portions, ingredientMap),
                "Should throw exception if description is empty");
    assertEquals("Description cannot be empty!", e.getMessage(), "Should throw correct exception");
  }

  @Test
  void testConstructorNullInstruction() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, null, portions, ingredientMap),
                "Should throw exception if instruction is null");
    assertEquals("Instruction cannot be empty!", e.getMessage(), "Should throw correct exception");
  }

  @Test
  void testConstructorEmptyInstruction() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new Recipe(name, description, "", portions, ingredientMap),
                "Should throw exception if instruction is empty");
    assertEquals("Instruction cannot be empty!", e.getMessage(), "Should throw correct exception");
  }

  @Test
  void testConstructorNullIngredientMap() {
    IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> new Recipe(name, description, instruction, portions, null),
                "Should throw exception if ingredientMap is null");
    assertEquals("A recipe cannot have zero ingredients!", e.getMessage(), "Should throw correct exception");
  }
 }