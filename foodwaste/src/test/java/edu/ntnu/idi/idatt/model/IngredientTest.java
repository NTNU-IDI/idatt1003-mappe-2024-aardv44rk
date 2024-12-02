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
  private Quantity quantity;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    name = "Milk";
    unitPrice = 10;
    expiryDate = DateUtil.parseDate("12-12-2024");
    quantity = new Quantity(2.0, "mL");

    ingredient = new Ingredient(name, unitPrice, expiryDate, quantity);
  }

  @Test
  void testConstructor() {
    assertEquals(name, ingredient.getName(), "Names should be equal");
    assertEquals(unitPrice, ingredient.getUnitPrice(), "Price should be equal");
    assertEquals(expiryDate, ingredient.getExpiryDate(), "Dates should be equal");
    assertEquals(quantity, ingredient.getQuantity(), "Quantities should equal");
  }

  @Test
  void testPrintIngredient() {
    String output = """
        Milk
        Price: 10.0 money units
        Amount: 2.0 mL
        Expiry date: 12-12-2024""";

    assertEquals(output, ingredient.printIngredient());
  }

  // Negative tests
  @Test
  void testNameEmptyOrNull() {
    IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient("", unitPrice, expiryDate, quantity),
        "IllegalArgumentException should be thrown if name is empty");
    assertEquals(
        "Name cannot be empty or null!", exception1.getMessage(), "Messages should match");
    IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(null, unitPrice, expiryDate, quantity),
        "IllegalArgumentException should be thrown if name is null");
    assertEquals(
        "Name cannot be empty or null!", exception2.getMessage(), "Messages should match");
  }

  @Test
  void testPriceNegativeOrZero() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, -1, expiryDate, quantity),
        "IllegalArgumentException should be thrown if price is negative or zero");
    assertEquals("Price cannot be negative or zero!", e.getMessage(), "Messages should match");
  }

  @Test
  void testDateNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, unitPrice, null, quantity),
        "IllegalArgumentException should be thrown if date is null");
    assertEquals("Date cannot be null!", e.getMessage(), "Messages should match");
  }

  @Test
  void testNullQuantity() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> new Ingredient(name, unitPrice, expiryDate, null),
        "IllegalArgumentException should be thrown if quantity is null");
    assertEquals("Quantity cannot be null!", e.getMessage(), "Messages should match");
  }

  @Test
  void testSetQuantity() {
    Quantity newQuantity = new Quantity(500.0, "mL");
    ingredient.setQuantity(newQuantity);
    assertEquals(ingredient.getQuantity(), newQuantity, "Amount should match");
  }

  @Test
  void testSetPrice() {
    double newPrice = 5.0;
    ingredient.setUnitPrice(newPrice);
    assertEquals(ingredient.getUnitPrice(), newPrice, "Price should match");
  }

  @Test
  void testSetQuantityNull() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> ingredient.setQuantity(null),
        "IllegalArgumentException should be thrown if quantity is null");
    assertEquals("Quantity cannot be null!", e.getMessage(), "Messages should match");
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