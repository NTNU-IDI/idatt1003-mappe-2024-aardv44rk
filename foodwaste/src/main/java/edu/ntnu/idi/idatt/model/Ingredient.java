package edu.ntnu.idi.idatt.model;

import java.util.Date;

/**
 * TEMP. TODO
 */
public class Ingredient {
  private String name;
  private double price;
  private Date expiryDate;
  private double amount;
  private String unit;

  /**
   * Ingredient constructor.
   * Validates arguments... TODO
   *
   * @param name The ingredients name
   * @param price Price per unit of the ingredient
   * @param expiryDate Date that the ingredient expires
   * @param amount Available amount of an ingredient 
   * @param unit The unit the ingredient is measured in
   */
  public Ingredient(String name, double price, Date expiryDate, double amount, String unit) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }
    if (price <= 0) {
      throw new IllegalArgumentException("Price cannot be 0 or negative.");
    }
    if (expiryDate == null) {
      throw new IllegalArgumentException("Date cannot be null.");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount cannot be 0 or negative");
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

  // Setters
  public void setPrice(double price) {
    this.price = price;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() { // TODO
    StringBuilder sb = new StringBuilder();

    return sb.toString();
  }
}