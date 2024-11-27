package edu.ntnu.idi.idatt.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt.util.DateUtil;

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
  private Ingredient ingredient5;
  private String name;
  private double price;
  private LocalDate expiryDate;
  private double amount;
  private String unit;
  private FoodStorage fs;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    fs = new FoodStorage();
    name = "Milk";
    price = 10;
    expiryDate = DateUtil.parseDate("12-12-2024");
    amount = 2.0;
    unit = "L";
    ingredient1 = new Ingredient(name, price, expiryDate, amount, unit);
    ingredient2 = new Ingredient(name, 12.00, expiryDate, 1.00, unit);
    ingredient3 = new Ingredient(name, 12.00, expiryDate, 1.00, unit);
    ingredient4 = new Ingredient("Banana", price, DateUtil.parseDate("12-12-2023"), amount, unit);
    ingredient5 = new Ingredient(name, price, DateUtil.parseDate("14-12-2024"), amount, unit);
  }

  // Positive cases
  @Test
  void testAddIngredient() {
    fs.addIngredient(ingredient1);
    assertTrue(fs.getStorage().containsKey(name));
    assertTrue(fs.searchIngredient(name).contains(ingredient1));
  }

  @Test
  void testAddIngredientMerge() {
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
  void testAddIngredientDifferentExpiryDates() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient5);
    assertEquals(2, fs.getStorage().get(name).size(), "Size should be 2");
  }
  
  @Test
  void testAddIngredientNewKeyInStorage() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient4);
    assertEquals(2, fs.getStorage().size(), "Size should be 2");
  }

  @Test
  void testAddIngredientNotMerge() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    assertEquals(2, fs.searchIngredient(name).size(), "Size should be 2");
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
  void testRemoveIngredientExcessAmount() {
    fs.addIngredient(ingredient1);
    assertFalse(fs.removeIngredient(name, 10), "Should return false if excess amount");
  }

  @Test
  void testRemoveIngredientNameOnly() {
    fs.addIngredient(ingredient1);
    assertTrue(fs.getStorage().containsKey(name));
    fs.removeIngredient(name);
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
    assertTrue(fs.getExpiredFood(DateUtil.parseDate("12-12-2024")).contains(ingredient4));
  }

  @Test
  void testGetTotalPrice() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    fs.addIngredient(ingredient3);
    double total = FoodStorage.getTotalPrice(fs.searchIngredient(name));
    assertEquals(44, total, "Price should equal");
  }

  @Test
  void testGetTotalAmount() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    fs.addIngredient(ingredient3);
    double total = FoodStorage.getTotalAmount(fs.searchIngredient(name));
    assertEquals(4, total, "Price should equal");
  }

  @Test
  void testContainsIngredient() {
    fs.addIngredient(ingredient1);
    assertTrue(fs.containsIngredient(name, amount));
  }

  @Test
  void testNotContainsIngredient() {
    assertFalse(fs.containsIngredient(name, amount));
    fs.addIngredient(ingredient2);
    assertFalse(fs.containsIngredient(name, amount));
  }

  // Negative cases TODO
  @Test
  void testAddIngredientNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> fs.addIngredient(null),
                "IllegalArgumentException should be thrown if null argument is passed to method");
    assertEquals("Ingredient cannot be null", e.getMessage(), "Messages should match");
  }

  @Test
  void testGetTotalPriceEmptyList() {
    List<Ingredient> ingredients = new ArrayList<>();
    IllegalStateException e = assertThrows(IllegalStateException.class, 
            () -> FoodStorage.getTotalPrice(ingredients),
            "Should throw an IllegalStateException for an empty List");
    assertEquals("List cannot be empty, whoops!", e.getMessage(), "Messages should match");
  }

  @Test
  void testGetTotalPriceNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
            () -> FoodStorage.getTotalPrice(null),
            "Should throw an IllegalArgumentException for a null List");
    assertEquals("List cannot be empty, whoops!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSearchIngredientEmptyStorage() {
    assertNotEquals(null, fs.searchIngredient(""), "Method should not return null");
    assertTrue(fs.searchIngredient("").isEmpty(), "Collections.emptyList() should be empty");
  }

  @Test
  void testSearchIngredientNotExist() {
    assertTrue(fs.searchIngredient("NonExistent").isEmpty());
  }

  @Test
  void testRemoveIngredientNotFound() {
    IllegalStateException e1 = assertThrows(IllegalStateException.class,
                  () -> fs.removeIngredient("", amount),
                  "IllegalStateException should be thrown if Ingredient not present");
    assertEquals("Ingredient not in storage", e1.getMessage());

    IllegalStateException e2 = assertThrows(IllegalStateException.class,
                  () -> fs.removeIngredient(""),
                  "IllegalStateException should be thrown if Ingredient not present");
    assertEquals("Ingredient not in storage", e2.getMessage()); 
  }

  @Test 
  void testRemoveIngredientInvalidAmount() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                  () -> fs.removeIngredient(name, -1),
                  "IllegalArgumentException should be thrown if negative or zero amount");
    assertEquals("Amount cannot be negative or zero!", e.getMessage());
  }

  @Test
  void testSortEmptyStorage() {
    Map<String, List<Ingredient>> storage = fs.sortStorage(fs.getStorage());
    assertTrue(storage.isEmpty());
  }

  @Test
  void testSortNullStorage() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                              () -> fs.sortStorage(null),
                              "Exception should be thrown if null map");
    assertEquals("Map cannot be null", e.getMessage(), "Messages should match");
  }
}