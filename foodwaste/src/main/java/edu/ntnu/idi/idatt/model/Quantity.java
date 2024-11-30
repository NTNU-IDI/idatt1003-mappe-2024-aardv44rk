package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;

/**
 * Responsible for handling units. TODO
 */
public class Quantity {
  private double amount;
  private String unit;

  public Quantity(double amount, String unit) {
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
}