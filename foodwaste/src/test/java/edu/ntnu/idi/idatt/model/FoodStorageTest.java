package edu.ntnu.idi.idatt.model;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    ingredient4 = new Ingredient(name, price, new Date(160000000l), amount, unit);
  }

  @Test
  void testAddIngredient() {
    fs.addIngredient(ingredient1);
    List<Ingredient> ingredients = fs.searchIngredient(name);
    
    assertEquals(1, ingredients.size(), "Sizes should match");
    assertEquals(ingredient1, ingredients.get(0), "Objects should match");
  } 
  
  @Test
  void testAddIngredientMerge() {
    fs.addIngredient(ingredient2);
    fs.addIngredient(ingredient3);
    List<Ingredient> ingredients = fs.searchIngredient(name);

    assertEquals(1, ingredients.size(), "Sizes should match");
    assertEquals(ingredient2, ingredients.get(0), "Objects should match");
  }

  @Test
  void testRemoveIngredient() {
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);

    List<Ingredient> ingredients = fs.searchIngredient(name);
    fs.removeIngredient(name, 2.5);

    assertEquals(1, ingredients.size(), "Sizes should match");
    assertEquals(0.5, ingredients.get(0).getAmount(), "Amounts should match");
  }

  @Test
  void testSearchIngredient() {
    fs.addIngredient(ingredient1);    
    assertTrue(fs.searchIngredient(ingredient1.getName()).contains(ingredient1),"Ingredient should exist");
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
    assertEquals(44,total,"Price should equal");
  }
}
