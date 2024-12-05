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
  private double unitPrice;
  private LocalDate expiryDate;
  private double amount;
  private String unit;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    name = "Milk";
    unitPrice = 10;
    expiryDate = DateUtil.parseDate("12-12-2024");
    amount = 2;
    unit = "mL";

    ingredient = new Ingredient(name, unitPrice, expiryDate, amount, unit);
  }

  @Test
  void testConstructor() {
    assertEquals(name, ingredient.getName(), "Names should be equal");
    assertEquals(unitPrice, ingredient.getUnitPrice(), "Price should be equal");
    assertEquals(expiryDate, ingredient.getExpiryDate(), "Dates should be equal");
    assertEquals(amount, ingredient.getAmount(), "Quantities should equal");
    assertEquals(unit, ingredient.getUnit(), "Units should be equal");
  }

  @Test
  void testPrintIngredient() {
    String output = """
        Milk
        Price: 10.0 money units
        Amount: 2.0 mL
        Expiry date: 12-12-2024""";

    assertEquals(output, ingredient.ingredientToString());
  }

  // Negative tests
  @Test
  void testNameEmptyOrNull() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient("", unitPrice, expiryDate, amount, unit),
        "IllegalArgumentException should be thrown if name is empty");
    assertEquals(
        "Name cannot be empty or null!", exception1.getMessage(), "Messages should match");
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(null, unitPrice, expiryDate, amount, unit),
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
        () -> new Ingredient(name, unitPrice, null, amount, unit),
        "IllegalArgumentException should be thrown if date is null");
    assertEquals("Date cannot be null!", e.getMessage(), "Messages should match");
  }

  @Test
  void testInvaidAmount() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, unitPrice, expiryDate, -1, unit),
        "IllegalArgumentException should be thrown if amount is negative");
    assertEquals("Amount cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testNullUnit() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, unitPrice, expiryDate, amount, null),
        "IllegalArgumentException should be thrown if quantity is null");
    assertEquals("Unit cannot be null!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSetAmount() {
    double newAmount = 500;
    ingredient.setAmount(newAmount);
    assertEquals(newAmount, ingredient.getAmount(), "Amount should match");
  }

  @Test
  void testSetPrice() {
    double newPrice = 5.0;
    ingredient.setUnitPrice(newPrice);
    assertEquals(ingredient.getUnitPrice(), newPrice, "Price should match");
  }

  @Test
  void testSetAmountZeroOrNegative() {
    double newAmount = -1;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> ingredient.setAmount(newAmount),
        "IllegalArgumentException should be thrown if amount is negative or zero");
    assertEquals("Amount cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSetPriceZeroOrNegative() {
    double newPrice = -1;
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> ingredient.setUnitPrice(newPrice),
        "IllegalArgumentException should be thrown if price is negative or zero");
    assertEquals("Price cannot be negative or zero!", e.getMessage(), "Messages should match");
  }
}