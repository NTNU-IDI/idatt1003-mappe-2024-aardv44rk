package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class responsible for testing all operations relating to Recipe objects.
 */
class RecipeTest {

  private String name;
  private String description;
  private String instruction;
  private List<Ingredient> ingredients;
  private double portions;

  private Ingredient ingredient1;
  private Ingredient ingredient2;
  private Recipe recipe;
  
  @BeforeEach
  void testInit() {
    name = "Oatmeal";
    description = "A mediocre meal consisting of oats and water.";
    instruction = "Boil water, add oats, bon apetit";
    ingredients = new ArrayList<>();
    ingredient1 = new Ingredient("Oats", 100, "g");
    ingredient2 = new Ingredient("Water", 100, "mL");
    ingredients.add(ingredient1);
    ingredients.add(ingredient2);
    portions = 1.0;
    recipe = new Recipe(name, description, instruction, ingredients, portions);

  }

  @Test
  void testConstructor() {
    assertEquals(name, recipe.getName(), "Name fields should match");
    assertEquals(description, recipe.getDescription(), "Description fields should match");
    assertEquals(instruction, recipe.getInstruction(), "Instruction fields should match");
    assertEquals(ingredients, recipe.getIngredients(), "Ingredient lists should match");
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
  void testSetIngredients() {
    List<Ingredient> expected = new ArrayList<>();
    Ingredient ingredient = new Ingredient("Milk", 100, "mL");
    expected.add(ingredient);
    expected.add(ingredient2);
    recipe.setIngredients(expected);
    assertEquals(expected, recipe.getIngredients());
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
        Recipe:
        Oatmeal
        A mediocre meal consisting of oats and water.
        * Oats 100.0 g
        * Water 100.0 mL

        Step by step:
        Boil water, add oats, bon apetit""", 
        recipe.printRecipe());
  }

  // Negative tests
  @Test
  void testSetNameNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
              () -> recipe.setName(null),
              "Should throw IllegalArgumentException if ");
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
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
              () -> recipe.setIngredients(null),
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
  void testSetIngredientsEmpty() {
    List<Ingredient> emptyList = new ArrayList<>();
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
              () -> recipe.setIngredients(emptyList),
              "Should throw IllegalArgumentException if empty list is passed");
    assertEquals("Recipe cannot have zero ingredients!", e.getMessage());
  }

  @Test
  void testSetPortionsNegative() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
              () -> recipe.setPortions(-1),
              "Should throw IllegalArgumentException if negative number is passed");
    assertEquals("Recipe cannot have zero or negative amount of portions!", e.getMessage());
  }
}