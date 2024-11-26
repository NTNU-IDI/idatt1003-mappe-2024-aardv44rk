package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class responsible for testing FoodStorage class. 
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 0.8
 */
class FoodStorageTest {
  private Ingredient ingredient1;
  private Ingredient ingredient2;
  private Ingredient ingredient3;
  private Ingredient ingredient4;
  private String name;
  private double price;
  private Date expiryDate;
  private double amount;
  private String unit;
  private FoodStorage fs;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    fs = new FoodStorage();
    name = "Milk";
    price = 10;
    expiryDate = new Date();
    amount = 2.0;
    unit = "L";
    ingredient1 = new Ingredient(name, price, expiryDate, amount, unit);
    ingredient2 = new Ingredient(name, 12.00, expiryDate, 1.00, unit);
    ingredient3 = new Ingredient(name, 12.00, expiryDate, 1.00, unit);
    ingredient4 = new Ingredient("Banana", price, new Date(160000000L), amount, unit);
  }

  // Positive cases
  @Test
  void testAddIngredient() {
    fs.addIngredient(ingredient2);
    List<Ingredient> ingredients = fs.searchIngredient(name);
    
    assertEquals(1, ingredients.size(), "Sizes should match");
    fs.addIngredient(ingredient3);

    assertEquals(1, ingredients.size(), "Sizes should match");
    assertEquals(2,
              ingredients.getFirst().getAmount(),
              "Amounts should match");
  } 
  
  @Test
  void testAddIngredientNewKeyInStorage() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient4);
    assertEquals(2, fs.getStorage().size(), "Size should be 2");
  }

  @Test
  void testRemoveIngredient() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);

    List<Ingredient> ingredients = fs.searchIngredient(name);
    assertEquals(true, fs.removeIngredient(name, 2.5), "Return value should be true");
    assertEquals(1, ingredients.size(), "Size should be 1");
    assertEquals(false, fs.removeIngredient(name, 0.8), "Return value should be false");
    assertFalse(fs.getStorage().containsKey(name));
  }

  @Test
  void testSearchIngredient() {
    fs.addIngredient(ingredient1);    
    assertTrue(fs.searchIngredient(ingredient1.getName()).contains(ingredient1),
               "Ingredient should exist");
  }

  @Test
  void testSortStorage() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient4);

    Map<String, List<Ingredient>> sortedStorage = fs.sortStorage(fs.getStorage());
    assertEquals("Banana", sortedStorage.keySet().toArray()[0]);
  }

  @Test
  void testGetExpired() {
    fs.addIngredient(ingredient4);
    assertTrue(fs.getExpiredFood(new Date()).contains(ingredient4));
  }

  @Test
  void testGetTotal() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    fs.addIngredient(ingredient3);
    double total = fs.getTotalPrice(fs.searchIngredient(name));
    assertEquals(44, total, "Price should equal");
  }

  // Negative cases TODO
  @Test
  void testAddIngredientNotMerge() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    assertEquals(2, fs.searchIngredient(name).size(), "Size should be 2");
  }

  @Test
  void testGetTotalPriceEmptyList() {
    List<Ingredient> ingredients = new ArrayList<>();
    double total = fs.getTotalPrice(ingredients);

    assertEquals(0, total, "Total should be zero");
  }

  @Test
  void testSearchIngredientEmptyStorage() {
    assertNotEquals(null, fs.searchIngredient(""), "Method should not return null");
    assertTrue(fs.searchIngredient("").isEmpty(), "Collections.emptyList() should be empty");
  }

  @Test
  void testRemoveIngredientThrows() {
    IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
                   () -> fs.removeIngredient("", amount),
                   "IllegalArgumentException should be thrown if Ingredient not present");
    assertEquals("Ingredient not in storage", e1.getMessage());

    IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
                  () -> fs.removeIngredient(name, -19),
                  "IllegalArgumentException should be thrown if negative or zero amount");
    assertEquals("Amount to remove cannot be negative or zero", e2.getMessage());
  }
}