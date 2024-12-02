package edu.ntnu.idi.idatt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Responsible for testing the Quantity class and its methods.
 */
class QuantityTest {
  double amount;
  String unit;
  Quantity quantity;

  @BeforeEach
  @SuppressWarnings("unused")
  void testInit() {
    amount = 100.0;
    unit = "g";
    quantity = new Quantity(amount, unit);
  }

  @Test
  void testConstructor() {
    assertEquals(amount, quantity.getAmount(), "Amounts should equal");
    assertEquals(unit, quantity.getUnit(), "Units should equal");
  }

  @Test
  void testSetAmount() {
    quantity.setAmount(2.0);
    assertEquals(2, quantity.getAmount(), "Amounts should equal");
  }

  @Test
  void testSetUnit() {
    quantity.setUnit("mL");
    assertEquals("mL", quantity.getUnit(), "Units should equal");
  }

  @Test
  void testConstructorInvalidAmount() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
          () -> new Quantity(-1, "mL"), "Should throw exception with negative or zero amount");

    assertEquals("Amount cannot be negative or zero!", e.getMessage(), "Messages should equal");
  }

  @Test
  void testConstructorInvalidUnit() {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
         () -> new Quantity(1, "banana"), "Should throw exception with invalid unit");

    assertEquals("Invalid unit: banana\nUnit must be one of: g, mL, pcs", 
                e.getMessage(), "Messages should match");
  }
}
