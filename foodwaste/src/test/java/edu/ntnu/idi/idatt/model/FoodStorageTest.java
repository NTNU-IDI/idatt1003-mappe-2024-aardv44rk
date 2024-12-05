package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.util.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class responsible for testing FoodStorage class.
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 1.1
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
    amount = 2000;
    unit = "mL";
    ingredient1 = new Ingredient(name, price, expiryDate, amount, unit);
    ingredient2 = new Ingredient(name, 12.00, expiryDate, amount, unit);
    ingredient3 = new Ingredient(name, 12.00, expiryDate, amount, unit);
    ingredient4 = new Ingredient("Banana", price, DateUtil.parseDate("12-12-2023"), amount, unit);
    ingredient5 = new Ingredient(name, price, DateUtil.parseDate("10-12-2024"), amount, unit);
  }

  // Positive cases
  @Test
  void testAddIngredient() {
    fs.addIngredient(ingredient1);
    assertTrue(fs.containsIngredient(name, 2000), "Storage should contain correct amount");
    assertTrue(fs.searchIngredient(name).contains(ingredient1), "List should contain object");
  }

  @Test
  void testAddIngredientMerge() {
    fs.addIngredient(ingredient2);
    List<Ingredient> ingredients = fs.searchIngredient(name);

    assertEquals(1, ingredients.size(), "Sizes should match");
    fs.addIngredient(ingredient3);

    assertEquals(1, ingredients.size(), "Sizes should match");
    assertEquals(400,
              ingredients.getFirst().getAmount(),
              "Amounts should match");
  }

  @Test
  void testMergeIngredientDifferentUnits() {
    ingredient2 = new Ingredient(name, 12.00, expiryDate, amount, "g");
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
                                () -> fs.mergeIngredient(ingredient2, ingredient3),
                                "Differing units when merging should throw exception");
    assertEquals("Unit differs from previous unit: " + ingredient3.getUnit(),
                  e.getMessage(), "Messages should be the same");
  }

  @Test
  void testAddIngredientNewKeyInStorage() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient4);
    assertEquals(2, fs.getStorage().size(), "Size should be 2");
  }

  @Test
  void testAddIngredientInsertionOrder() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient5);
    assertEquals(ingredient5, fs.getStorage().get(name).get(0), "Index should be 0");
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
    assertEquals(true, fs.removeIngredient(name, 2100), "Return value should be true");
    assertEquals(1, ingredients.size(), "Size should be 1");
  }

  @Test
  void testRemoveIngredientExcessAmount() {
    fs.addIngredient(ingredient1);
    assertFalse(fs.removeIngredient(name, 2001), "Should return false if excess amount");
  }

  @Test
  void testRemoveIngredientNameOnly() {
    fs.addIngredient(ingredient1);
    assertTrue(fs.getStorage().containsKey(name), "Storage should contain ingredient");
    fs.removeIngredient(name);
    assertFalse(fs.getStorage().containsKey(name), "Storage should not contain ingredient");
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
    assertEquals(
              "Banana".toLowerCase(), 
              sortedStorage.keySet().toArray()[0], 
              "Banana should be first index"
    );
  }

  @Test
  void testGetExpired() {
    fs.addIngredient(ingredient4);
    assertTrue(
            fs.getExpiredFood(DateUtil.parseDate("12-12-2024")).contains(ingredient4),
            "List should contain ingredient"
    );
  }

  @Test
  void testGetExpiredNoExpired() {
    fs.addIngredient(ingredient1);
    assertTrue(
        fs.getExpiredFood(DateUtil.parseDate("12-12-2024")).isEmpty(),
        "Should not contain ingerdient"
    );
  }

  @Test
  void testGetExpiredEmptyStorage() {
    assertTrue(
        fs.getExpiredFood(DateUtil.parseDate("12-12-2024")).isEmpty(),
        "Should return empty list"
    );
  }

  @Test
  void testGetTotalPrice() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    fs.addIngredient(ingredient3);
    double total = FoodStorage.getTotalPrice(fs.searchIngredient(name));
    assertEquals(34, total, "Price should equal");
  }

  @Test
  void testGetTotalPriceEmptyList() {
    List<Ingredient> emptyList = new ArrayList<>();
    assertEquals(0, FoodStorage.getTotalPrice(emptyList), "Empty list should return 0");
  }

  @Test
  void testGetTotalAmount() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
    fs.addIngredient(ingredient3);
    double total = FoodStorage.getTotalAmount(fs.searchIngredient(name));
    assertEquals(2400, total, "Amount should equal");
  }

  @Test
  void testGetTotalAmountEmptyList() {
    List<Ingredient> emptyList = new ArrayList<>();
    assertEquals(0, FoodStorage.getTotalAmount(emptyList), "Empty list should return 0");
  }

  @Test
  void testContainsIngredient() {
    fs.addIngredient(ingredient1);
    assertTrue(fs.containsIngredient(name, 2000.0), "Should contain ingredient1");
  }

  @Test
  void testNotContainsIngredient() {
    assertFalse(fs.containsIngredient(name, 300.0), "Should not contain any ingredient");
    fs.addIngredient(ingredient2);
    assertFalse(fs.containsIngredient(name, 300.0), "Should not contain more than 200");
  }

  @Test
  void testFindIndexAllMatch() {
    List<Ingredient> ingredients = List.of(ingredient4, ingredient5);
    Ingredient target = ingredient1;
    int index = fs.findIndex(
                ingredients, 
                target, 
                Comparator.comparing(Ingredient::getExpiryDate)
    );

    assertEquals(2, index, "Index should be 2");
  }

  // Negative cases
  @Test
  void testAddIngredientNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> fs.addIngredient(null),
                "IllegalArgumentException should be thrown if null argument is passed to method");
    assertEquals("Ingredient cannot be null", e.getMessage(), "Messages should match");
  }

  @Test
  void testAddIngredientEdgeCases() {
    fs.addIngredient(new Ingredient(name, price, LocalDate.MAX, amount, unit));
    fs.addIngredient(new Ingredient(name, price, LocalDate.MIN, amount, unit));

    assertTrue(fs.getStorage().containsKey(name), "Storage should contain edge case Ingredients");
    assertEquals(2, fs.getStorage().get(name).size(), "Both edge cases should have been added");
  }

  @Test
  void testGetTotalPriceNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> FoodStorage.getTotalPrice(null),
            "Should throw an IllegalArgumentException for a null List");
    assertEquals("List cannot be null, whoops!", e.getMessage(), "Messages should match");
  }

  @Test
  void getTotalAmountNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> FoodStorage.getTotalAmount(null),
            "Should throw an IllegalArgumentException for a null List");
    assertEquals("List cannot be null, whoops!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSearchIngredientEmptyStorage() {
    assertNotEquals(null, fs.searchIngredient(""), "Method should not return null");
    assertTrue(fs.searchIngredient("").isEmpty(), "Collections.emptyList() should be empty");
  }

  @Test
  void testSearchIngredientNotExist() {
    assertTrue(fs.searchIngredient("NonExistent").isEmpty(), "Should return empty list");
  }

  @Test
  void testRemoveIngredientNotFound() {
    IllegalStateException e = assertThrows(IllegalStateException.class,
                  () -> fs.removeIngredient("", 2.0),
                  "IllegalStateException should be thrown if Ingredient not present");
    assertEquals("Ingredient not in storage", e.getMessage(), "Messages should match");
  }

  @Test
  void testRemoveIngredientNameOnlyNotFound() {
    IllegalStateException e = assertThrows(IllegalStateException.class,
                  () -> fs.removeIngredient(""),
                  "IllegalStateException should be thrown if Ingredient not present");
    assertEquals("Ingredient not in storage", e.getMessage(), "Messages should match");
  }

  @Test
  void testRemoveIngredientInvalidAmount() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                  () -> fs.removeIngredient(name, -1),
                  "IllegalArgumentException should be thrown if negative or zero amount");
    assertEquals("Amount cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSortEmptyStorage() {
    Map<String, List<Ingredient>> storage = fs.sortStorage(fs.getStorage());
    assertTrue(storage.isEmpty(), "Should return empty list");
  }

  @Test
  void testSortNullStorage() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                              () -> fs.sortStorage(null),
                              "Exception should be thrown if null map");
    assertEquals("Map cannot be null", e.getMessage(), "Messages should match");
  }
}