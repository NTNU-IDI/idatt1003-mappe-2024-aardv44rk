package edu.ntnu.idi.idatt.model;

import java.util.Date;

/**
 * <code>Ingredient</code> class is a class. TODO
 */
public class Ingredient {
  private final String name;
  private double price;
  private double amount;
  private String unit;
  private Date expiryDate;

  /**
   * Sole constructor.
   *
   * @param name Name of the ingredient
   * @param price Price per unit of the ingredient
   * @param expiryDate Expirydate of the ingredient
   * @param amount Amount of the ingredient
   * @param unit Unit of the ingredient (e.g. L, kg)
   */
  public Ingredient(String name, double price, Date expiryDate, double amount, String unit) {
    this.name = name;
    this.price = price;
    this.expiryDate = expiryDate;
    this.amount = amount;
    this.unit = unit;
  }

  // getters
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

  // setters
  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name)
        .append("\n Price: ").append(price)
        .append("\n Amount: ").append(amount)
        .append("\n Expiry Date: ").append(expiryDate)
        .append("\n\n");
    return sb.toString();
  }
}