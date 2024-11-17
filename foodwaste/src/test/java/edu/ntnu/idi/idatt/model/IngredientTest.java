package edu.ntnu.idi.idatt.model;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    // Name
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(null, price, expiryDate, amount, unit),
                "Exception should be thrown if null name");
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient("", price, expiryDate, amount, unit),
                "Exception should be thrown if empty name");
    // Price
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, 0, expiryDate, amount, unit),
                "Exception should be thrown if zero price");
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, -1, expiryDate, amount, unit),
                "Exception should be thrown if negative price");
    // Expiry date
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, null, amount, unit),
                "Exception should be thrown if null date");
    // Amount
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, expiryDate, 0, unit),
                "Exception should be thrown if zero amount");
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, expiryDate, -1, unit),
                "Exception should be thrown if negative amount");
    // Unit
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, expiryDate, amount, null),
                "Exception should be thrown if null unit");
    assertThrows(IllegalArgumentException.class,
                () -> new Ingredient(name, price, expiryDate, amount, ""),
                "Exception should be thrown if empty unit");

  }
 
  @Test
  void testSetters() {
    String name = "Egg";
    double price = 10;
    Date expiryDate = new Date();
    double amount = 1.0;
    String unit = "stk";

    Ingredient ingredient = new Ingredient(name, price, expiryDate, amount, unit);

    double newAmount = 2.0;
    ingredient.setAmount(newAmount);
    assertEquals(ingredient.getAmount(), newAmount);
    
    // TODO setters
  }
  // TODO tostring?

  // Trengs dette engang?
}
