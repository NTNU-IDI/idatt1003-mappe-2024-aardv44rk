package edu.ntnu.idi.idatt.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.util.DateUtil;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class responsible for testing the Ingredient class.
 *
 * @author @aardv44rk
 * @since November 18th 2024
 * @version 1.0
 */
class IngredientTest {
  private Ingredient ingredient;
  private String name;
  private double price;
  private LocalDate expiryDate;
  private double amount;
  private String unit;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    name = "Milk";
    price = 10;
    expiryDate = DateUtil.parseDate("12-12-2024");
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
  }

  @Test
  void testPrintIngredient() {
    String output = """
        Milk
        Price: 10.0 money units
        Amount: 2.0 L
        Expiry date: 12-12-2024""";

    assertEquals(output, ingredient.printIngredient());
  }

  // Negative tests
  @Test
  void testNameEmptyOrNull() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient("", price, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if name is empty");
    assertEquals(
        "Name cannot be empty or null!", exception1.getMessage(), "Messages should match");
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(null, price, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if name is null");
    assertEquals(
        "Name cannot be empty or null!", exception2.getMessage(), "Messages should match");
  }

  @Test
  void testPriceNegativeOrZero() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, -1, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if price is negative or zero");
    assertEquals("Price cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testDateNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, null, amount, unit),
        "IllegalArgumentException should be thrown if date is null");
    assertEquals("Date cannot be null!", e.getMessage(), "Messages should match");
  }

  @Test
  void testAmountZeroOrNegative() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, expiryDate, 0, unit),
        "IllegalArgumentException should be thrown if amount is negative or zero");
    assertEquals("Amount cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testUnitNullOrEmpty() {
    IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, expiryDate, amount, ""),
        "IllegalArgumentException should be thrown if unit is empty");
    assertEquals("Unit cannot be empty or null!", e1.getMessage(), "Messages should match");
    
    IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, expiryDate, amount, null),
        "IllegalArgumentException should be thrown if unit is null");
    assertEquals("Unit cannot be empty or null!", e2.getMessage(), "Messages should match");
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
  void testSetAmountZeroOrNegative() {
    double newAmount = 0;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> ingredient.setAmount(newAmount),
        "IllegalArgumentException should be thrown if amount is negative or zero");
    assertEquals("Amount cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSetPriceZeroOrNegative() {
    double newPrice = -1;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> ingredient.setPrice(newPrice),
        "IllegalArgumentException should be thrown if price is negative or zero");
    assertEquals("Price cannot be negative or zero!", e.getMessage(), "Messages should match");
  }
}