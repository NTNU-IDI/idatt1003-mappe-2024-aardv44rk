package edu.ntnu.idi.idatt.model;

import java.util.Date;

/**
 * The ingredient 
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 0.8
 */
public class Ingredient {
  private String name;
  private double price;
  private Date expiryDate;
  private double amount;
  private String unit;

  /**
   * Ingredient constructor. Initializes an object of class <code>Ingredient</code>
   *
   * @param name A String representing the name of an <code>Ingredient</code>
   * @param price A double representing the price per unit of the <code>Ingredient</code>
   * @param expiryDate A Date representing the date an <code>Ingredient</code> expires
   * @param amount A double representing the amount of an <code>Ingredient</code>
   * @param unit A string representing the unit an <code>Ingredient</code> is measured in
   * @throws IllegalArgumentException if values are invalid
   */
  public Ingredient(String name, double price, Date expiryDate, double amount, String unit) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty."); // TODO remove messages
    }
    if (price <= 0) {
      throw new IllegalArgumentException("Price cannot be 0 or negative.");
    }
    if (expiryDate == null) {
      throw new IllegalArgumentException("Date cannot be null.");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount cannot be 0 or negative.");
    }
    if (unit == null || unit.trim().isEmpty()) {
      throw new IllegalArgumentException("Unit cannot be null or empty.");
    }

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

  public Date getExpiryDate() {
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
    if (price <= 0) {
      throw new IllegalArgumentException("Price should not be negative or zero");
    }
    this.price = price;
  }
  // TODO Input validator and bound

  /**
   * Sets the amount attribute of an object.
   *
   * @param amount new amount of object
   * @throws IllegalArgumentException if amount <= 0
   */
  public void setAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount should not be negative or zero");
    }
    this.amount = amount;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append("\n")
      .append("Price: ").append(price).append("money units\n")
      .append("Amount: ").append(amount).append(" ").append(unit).append("\n")
      .append("Expiry date: ").append(expiryDate);
    return sb.toString();
  }
}