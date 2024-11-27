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
        Expiry date: 01-01-1970""";

    assertEquals(output, ingredient.printIngredient());
  }

  // Negative tests
  @Test
  void testNameThrows() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient("", price, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if name is empty");
    assertEquals(
        "Name cannot be null or empty.", exception1.getMessage(), "Messages should match");
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(null, price, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if name is null");
    assertEquals(
        "Name cannot be null or empty.", exception2.getMessage(), "Messages should match");
  }

  @Test
  void testPriceThrows() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, -1, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if price is negative or zero");
    assertEquals("Price cannot be 0 or negative.", e.getMessage(), "Messages should match");
  }

  @Test
  void testDateThrows() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, null, amount, unit),
        "IllegalArgumentException should be thrown if date is null");
    assertEquals("Date cannot be null.", e.getMessage(), "Messages should match");
  }

  @Test
  void testAmountThrows() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, expiryDate, 0, unit),
        "IllegalArgumentException should be thrown if amount is negative or zero");
    assertEquals("Amount cannot be 0 or negative.", e.getMessage(), "Messages should match");
  }

  @Test
  void testUnitThrows() {
    IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, expiryDate, amount, ""),
        "IllegalArgumentException should be thrown if unit is empty");
    assertEquals("Unit cannot be null or empty.", e1.getMessage(), "Messages should match");
    
    IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, price, expiryDate, amount, null),
        "IllegalArgumentException should be thrown if unit is null");
    assertEquals("Unit cannot be null or empty.", e2.getMessage(), "Messages should match");
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
        "IllegalArgumentException should be thrown if amount is negative or zero");
    assertEquals("Amount should not be negative or zero", e.getMessage(), "Messages should match");
  }

  @Test
  void testSetPriceThrows() {
    double newPrice = -1;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> ingredient.setPrice(newPrice),
        "IllegalArgumentException should be thrown if price is negative or zero");
    assertEquals("Price should not be negative or zero", e.getMessage(), "Messages should match");
  }
}