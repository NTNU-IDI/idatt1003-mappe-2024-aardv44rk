package edu.ntnu.idi.idatt.model;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodStorageTest {
  private Ingredient ingredient1;
  private Ingredient ingredient2;
  private Ingredient ingredient3;
  private String name;
  private double price;
  private Date expiryDate;
  private double amount;
  private String unit;
  private FoodStorage fs;

  @BeforeEach
  void testInit() {
    name = "Milk";
    price = 9.95;
    expiryDate = new Date();
    amount = 2.0;
    unit = "L";
    ingredient1 = new Ingredient(name, price, expiryDate, amount, unit);
    ingredient2 = new Ingredient(name, price + 2, expiryDate, amount - 1, unit);
    ingredient3 = new Ingredient(name, price + 2, expiryDate, amount - 1, unit);
    fs.addIngredient(ingredient1);
    fs.addIngredient(ingredient2);
  }

  @Test
  void testAddIngredient() {
    fs.addIngredient(ingredient3);
    // assertEquals(expected, actual);
  }
  

  @Test
  void testRemoveIngredient() {

  }

  @Test
  void testSearchIngredient() {

  }

  @Test
  void testGetExpiredFood() {

  }

  @Test
  void testGetTotalValue() {

  }
}
