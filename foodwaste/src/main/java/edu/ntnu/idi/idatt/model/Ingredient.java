package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;
import edu.ntnu.idi.idatt.util.DateUtil;
import java.time.LocalDate;


/**
 * The ingredient class is responsible for creating ingredients and provides
 * getters, setters, and a print method.
 *
 * @author @aardv44rk
 * @since November 17th 2024
 * @version 2.0
 */
public class Ingredient {
  private final String name;
  private double unitPrice;
  private final LocalDate expiryDate;
  private double amount;
  private String unit;
  
  /**
   * Constructs an Ingredient with a name, price, expiration date, amount, and unit.
   *
   * @param name A String representing the name of an <code>Ingredient</code>
   * @param price A double representing the price per unit of the <code>Ingredient</code>
   * @param expiryDate A Date representing the date an <code>Ingredient</code> expires
   * @param amount A double representing the amount of an <code>Ingredient</code>
   * @param unit A String representing the unit of an <code>Ingredient</code>
   */
  public Ingredient(String name, double price, LocalDate expiryDate, double amount, String unit) {
    ArgumentValidator.isValidIngredient(name, price, expiryDate, amount, unit);
    this.name = name;
    this.unitPrice = price;
    this.expiryDate = expiryDate;
    this.amount = amount;
    this.unit = unit;
  }

  /**
   * Constructor specifically for Ingredient objects used in Recipe objects.
   *
   * @param name A String representing the name of an <code>Ingredient</code>
   * @param amount A double representing the amount of an <code>Ingredient</code>
   * @param unit A String representing the unit of an <code>Ingredient</code>
   */
  public Ingredient(String name, double amount, String unit) {
    this.name = name;
    this.unitPrice = -1;
    this.expiryDate = LocalDate.MAX;
    this.amount = amount;
    this.unit = unit;
  }

  // Getters
  public String getName() {
    return name;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public double getAmount() {
    return amount;
  }

  public String getUnit() {
    return unit;
  }

  /**
   * Sets the price attribute of an object.
   *
   * @param price new price of object
   * @throws IllegalArgumentException if price <= 0
   */
  public void setUnitPrice(double price) throws IllegalArgumentException {
    ArgumentValidator.isValidDouble(price, "Price cannot be negative or zero!");
    this.unitPrice = price;
  }

  /**
   * Sets the amount attribute of an object.
   *
   * @param amount new amount of object
   * @throws IllegalArgumentException if amount <= 0
   */
  public void setAmount(double amount) throws IllegalArgumentException {
    ArgumentValidator.isValidDouble(amount, "Amount cannot be negative or zero!");
    this.amount = amount;
  }

  /**
   * Method that returns a String for an <code>Ingredient</code>. Expiry dates that are
   * in the past are printed in red.
   * 
   * <p>For use in printing tables of ingredients.</p>
   *
   * @return A string containing the attributes of an <code>Ingredient</code>
   */
  public String ingredientToString() {
    final String red = "\u001B[31m";
    final String reset = "\u001B[0m";
    String formattedDate = "";
    if (expiryDate.isBefore(LocalDate.now())) {
      formattedDate = red + DateUtil.formatDate(expiryDate) + reset;
    } else {
      formattedDate = DateUtil.formatDate(expiryDate);
    }
    return String.format(
          "%-20s %-10.2f %-25s %-10.2f %-10s",
          name, 
          unitPrice, 
          formattedDate,
          amount, 
          unit
    );
  }
}