package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * Unit test class responsible for testing the ingredient class.
 */
public class IngredientTest {
  
  @Test
  void testConstructorAndGetters() {
    String name = "Egg";
    double price = 10;
    Date expiryDate = new Date();
    double amount = 1.0;
    String unit = "stk";

    Ingredient ingredient = new Ingredient(name, price, expiryDate, amount, unit);

    assertEquals(name, ingredient.getName(), "Name does not match");
    assertEquals(price, ingredient.getPrice(), "Price does not match");
    assertEquals(expiryDate, ingredient.getExpiryDate(), "Dates don't match");
    assertEquals(amount, ingredient.getAmount(), "Amount does not match");
    assertEquals(unit, ingredient.getUnit(), "Unit does not match");
  }

  // void testSetters() {

  // }
  
  // void testToString() {

  // }

  // Trengs dette engang?
}
