package edu.ntnu.idi.idatt.model;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class responsible for testing the Ingredient class.
 */
public class IngredientTest {
  private Ingredient ingredient;
  private String name;
  private double price;
  private Date expiryDate;
  private double amount;
  private String unit;

  @BeforeEach
  void testInit() {
    name = "Milk";
    price = 9.95;
    expiryDate = new Date();
    amount = 2.0;
    unit = "L";

    ingredient = new Ingredient(name, price, expiryDate, amount, unit);
  }


  @Test
  void testConstructors() {
    assertEquals(name, ingredient.getName(), "Names should be equal");
    assertEquals(price, ingredient.getPrice(), "Price should be equal");
    assertEquals(expiryDate, ingredient.getExpiryDate(), "Dates should be equal");
    assertEquals(amount, ingredient.getAmount(), "Amount should be equal");
    assertEquals(unit, ingredient.getUnit(), "Unit should be equal");
    
    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient("", price, expiryDate, amount, unit),
               "IllegalArgumentException should be thrown if name is empty"
    );
    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient(null, price, expiryDate, amount, unit),
               "IllegalArgumentException should be thrown if name is null"
    );
    
    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient(name, -1, expiryDate, amount, unit),
               "IllegalArgumentException should be thrown if price is negative or zero"
    );

    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient(name, price, null, amount, unit),
               "IllegalArgumentException should be thrown if date is null"
    );

    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient(name, price, expiryDate, 0, unit),
               "IllegalArgumentException should be thrown if amount is negative or zero"
    );

    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient(name, price, expiryDate, amount, ""),
               "IllegalArgumentException should be thrown if unit is empty"
    );
    assertThrows(IllegalArgumentException.class, 
                () -> new Ingredient(name, price, expiryDate, amount, null),
               "IllegalArgumentException should be thrown if unit is null"
    );
    // TODO Replace with for-loop ?? Alternatives?
  }

  @Test
  void testSetAmount() {
    double newAmount = 5.0;
    ingredient.setAmount(newAmount);
    assertEquals(ingredient.getAmount(), newAmount, "Amount should match");
  }

  @Test
  void testSetPrice() {
    double newPrice = 5.0;
    ingredient.setPrice(newPrice);
    assertEquals(ingredient.getPrice(), newPrice, "Price should match");
  }

  @Test
  void testSetAmountThrows() {
    double newAmount = 0;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
                () -> ingredient.setAmount(newAmount),
               "IllegalArgumentException should be thrown if amount is negative or zero"
    );
    assertEquals("Amount should not be negative or zero", e.getMessage(), "Messages should match");
  }

  @Test
  void testSetPriceThrows() {
    double newPrice = -1;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, 
                () -> ingredient.setPrice(newPrice), 
                "IllegalArgumentException should be thrown if price is negative or zero"
    );
    assertEquals("Price should not be negative or zero", e.getMessage(), "Messages should match");
  }


}