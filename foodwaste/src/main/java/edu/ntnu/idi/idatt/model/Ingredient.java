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
 * @version 1.0
 */
public class Ingredient {
  private final String name;
  private double price;
  private final LocalDate expiryDate;
  private double amount;
  private final String unit;
  
  /**
   * Constructs an Ingredient with a name, price, expiryDate amount and unit.
   *
   * @param name A String representing the name of an <code>Ingredient</code>
   * @param price A double representing the price per unit of the <code>Ingredient</code>
   * @param expiryDate A Date representing the date an <code>Ingredient</code> expires
   * @param amount A double representing the amount of an <code>Ingredient</code>
   * @param unit A string representing the unit an <code>Ingredient</code> is measured in
   * @throws IllegalArgumentException if values are invalid
   */
  public Ingredient(String name, double price, LocalDate expiryDate, double amount, String unit) {
    ArgumentValidator.isValidString(name, "Name cannot be empty or null!");
    ArgumentValidator.isValidDouble(price, "Price cannot be negative or zero!");
    ArgumentValidator.isValidDate(expiryDate, "Date cannot be null!");
    ArgumentValidator.isValidDouble(amount, "Amount cannot be negative or zero!");
    ArgumentValidator.isValidString(unit, "Unit cannot be empty or null!");

    this.name = name;
    this.price = price;
    this.expiryDate = expiryDate;
    this.amount = amount;
    this.unit = unit;
  }

  // Getters
  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
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
   * @throws IllegalArgumentException if amount <= 0
   */
  public void setPrice(double price) {
    ArgumentValidator.isValidDouble(price, "Price cannot be negative or zero!");
    this.price = price;
  }

  /**
   * Sets the amount attribute of an object.
   *
   * @param amount new amount of object
   * @throws IllegalArgumentException if amount <= 0
   */
  public void setAmount(double amount) {
    ArgumentValidator.isValidDouble(amount, "Amount cannot be negative or zero!");
    this.amount = amount;
  }

  /**
   * Method that functions as a toString for an <code>Ingredient</code>.
   *
   * @return A string containing the attributes of an <code>Ingredient</code>
   */
  public String printIngredient() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.name).append("\n")
      .append("Price: ").append(this.price).append(" money units\n")
      .append("Amount: ").append(this.amount).append(" ").append(this.unit).append("\n")
      .append("Expiry date: ").append(DateUtil.formatDate(this.expiryDate));
    return sb.toString();
  }
}