package edu.ntnu.idi.idatt.model;

import java.time.LocalDate;

import edu.ntnu.idi.idatt.util.ArgumentValidator;
import edu.ntnu.idi.idatt.util.DateUtil;

/**
 * The ingredient class is responsible for creating ingredients and provides
 * getters, setters, and a print method.
 *
 * @author @aardv44rk
 * @since November 17th 2024
 * @version 1.1
 */
public class Ingredient {
  private final String name;
  private double unitPrice;
  private final LocalDate expiryDate;
  private Quantity quantity;
  
  /**
   * Constructs an Ingredient with a name, price, expiration date, amount, and unit.
   *
   * @param name A String representing the name of an <code>Ingredient</code>
   * @param price A double representing the price per unit of the <code>Ingredient</code>
   * @param expiryDate A Date representing the date an <code>Ingredient</code> expires
   * @param quantity Quantity object with amount and unit of Ingredient object
   */
  public Ingredient(String name, double price, LocalDate expiryDate, Quantity quantity) {
    ArgumentValidator.isValidIngredient(name, price, expiryDate, quantity);
    this.name = name;
    this.unitPrice = price;
    this.expiryDate = expiryDate;
    this.quantity = quantity;
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

  public Quantity getQuantity() {
    return quantity;
  }

  /**
   * Sets the price attribute of an object.
   *
   * @param price new price of object
   * @throws IllegalArgumentException if amount <= 0
   */
  public void setUnitPrice(double price) {
    ArgumentValidator.isValidDouble(price, "Price cannot be negative or zero!");
    this.unitPrice = price;
  }

  /**
   * Sets the quantity attribute of an Ingredient.
   *
   * @param quantity new amount and unit of object stored as a Quantity object
   */
  public void setQuantity(Quantity quantity) {
    ArgumentValidator.isValidObject(quantity, "Quantity cannot be null!");
    this.quantity = quantity;
  }

  /**
   * Method that functions as a toString for an <code>Ingredient</code>.
   *
   * @return A string containing the attributes of an <code>Ingredient</code>
   */
  public String printIngredient() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.name).append("\n")
      .append("Price: ").append(this.unitPrice).append(" money units\n")
      .append("Amount: ").append(this.quantity.getAmount())
      .append(" ").append(this.quantity.getUnit()).append("\n")
      .append("Expiry date: ").append(DateUtil.formatDate(this.expiryDate));
    return sb.toString();
  }
}