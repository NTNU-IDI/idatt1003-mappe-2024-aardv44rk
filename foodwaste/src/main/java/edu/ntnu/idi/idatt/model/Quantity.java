package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;

/**
 * Represents quantity of an Ingredient with an amount and unit.
 */
public class Quantity {
  private double amount;
  private String unit;

  /**
   * Sole constructor. Validates inputs with <code>ArgumentValidator.isValidQuantity</code>.
   *
   * @param amount double representing amount
   * @param unit String representing unit
   */
  public Quantity(double amount, String unit) {
    ArgumentValidator.isValidQuantity(amount, unit);
    this.amount = amount;
    this.unit = unit;
  }

  public double getAmount() {
    return amount;
  }

  public String getUnit() {
    return unit;
  }

  public void setAmount(double amount) {
    ArgumentValidator.isValidDouble(amount, "Amount cannot be negative or zero!");
    this.amount = amount;
  }
  
  public void setUnit(String unit) {
    ArgumentValidator.isValidString(unit, "Unit cannot be empty or null!");
    this.unit = unit;
  }

  public String quantityString() {
    return (this.amount + " " + this.unit);
  }
}