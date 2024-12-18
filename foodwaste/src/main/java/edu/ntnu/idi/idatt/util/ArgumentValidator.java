package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Ingredient;
import java.time.LocalDate;
import java.util.Map;




/**
 * Class responsible for handling all input validation in the program. 
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 1.1
 */

public class ArgumentValidator {
  
  /**
   * Constructor that throws an UnsupportedOperationException to prevent instantiation of the class.
   *
   * @throws UnsupportedOperationException if the class is instantiated 
   */
  private ArgumentValidator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Validates user input <code>s</code>. Throws IllegalArgumentException 
   * with message <code>m</code> if <code>s</code> is an empty string or 
   * null, else does nothing.
   *
   * @param s the <code>String</code> to be validated
   * @param m the message of the thrown exception
   * @throws IllegalArgumentException if <code>s</code> is empty or null
   */
  public static void isValidString(String s, String m) throws IllegalArgumentException {
    if (s == null || s.isBlank()) {
      throw new IllegalArgumentException(m);
    }
  }

  /**
   * Validates user input <code>d</code>. Throws IllegalArgumentException 
   * with message <code>m</code> if <code>d</code> is less than or equal to zero, 
   * else does nothing.
   *
   * @param d the <code>double</code> to be validated
   * @param m the message of the thrown exception
   * @throws IllegalArgumentException if <code>d</code> is empty or null
   */
  public static void isValidDouble(double d, String m) throws IllegalArgumentException {
    if (d <= 0) {
      throw new IllegalArgumentException(m);
    }
  }

  /**
   * Validates user input <code>o</code>. Throws IllegalArgumentException with message
   * <code>m</code> if <code>o</code> is null, else does nothing.
   *
   * @param o the object to be validated
   * @param m the message of the thrown Exception
   * @throws IllegalArgumentException if <code>o</code> is null
   */
  public static void isValidObject(Object o, String m) throws IllegalArgumentException {
    if (o == null) {
      throw new IllegalArgumentException(m);
    }
  }
  
  /**
   * Validates user input <code>unit</code>. Throws IllegalArgumentException if <code>unit</code>
   * is null or not one of: "g", "mL", "pcs". Else does nothing.
   *
   * @param unit the String to be validated
   * @throws IllegalArgumentException if <code>unit</code> is null or neither one of g, mL or pcs
   */
  public static void isValidUnit(String unit) throws IllegalArgumentException {
    if (
        unit == null 
        || !(unit.trim().equalsIgnoreCase("g") 
        || unit.trim().equalsIgnoreCase("mL") 
        || unit.trim().equalsIgnoreCase("pcs"))
    ) {
      throw new IllegalArgumentException(
          "Invalid unit: " + unit + "\nUnit must be one of: g, mL, pcs"
        );
    }
  }

  /**
   * Validates all parameters of an Ingredient object.
   *
   * @param name String representing name of an Ingredient
   * @param price Double representing price of an Ingredient
   * @param expiryDate LocalDate representing expiration date
   * @param amount Double representing amount of an Ingredient
   * @param unit String representing unit of an Ingredient
   */
  public static void isValidIngredient(
                        String name, 
                        double price, 
                        LocalDate expiryDate, 
                        double amount,
                        String unit
  ) {
    isValidString(name, "Name cannot be empty or null!");
    isValidDouble(price, "Price cannot be negative or zero!");
    isValidObject(expiryDate, "Date cannot be null!");
    isValidDouble(amount, "Amount cannot be negative or zero!");
    isValidUnit(unit);
  }

  /**
   * Validates all parameters of an Ingredient object, specifically one for use in Recipes.
   *
   * @param name String representing name of an Ingredient
   * @param amount Double representing amount of an Ingredient
   * @param unit String representing unit of an Ingredient
   */
  public static void isValidIngredient(
                        String name, 
                        double amount,
                        String unit
  ) {
    isValidString(name, "Name cannot be empty or null!");
    isValidDouble(amount, "Amount cannot be negative or zero!");
    isValidUnit(unit);
  }

  /**
   * Validates all parameters of a Recipe object.
   *
   * @param name String representing name of the recipe
   * @param description String representing description of the recipe
   * @param instruction String representing instruction of the recipe
   * @param ingredientMap Map with key String and value Quantity holding recipe ingredients
   * @param portions double representing the amount of servings
   */
  public static void isValidRecipe(String name, 
                String description, 
                String instruction,
                Map<String, Ingredient> ingredientMap, 
                double portions
  ) {
    ArgumentValidator.isValidString(name, "Name cannot be empty!");
    ArgumentValidator.isValidString(description, "Description cannot be empty!");
    ArgumentValidator.isValidString(instruction, "Instruction cannot be empty!");
    ArgumentValidator.isValidMap(ingredientMap, "A recipe cannot have zero ingredients!");
    ArgumentValidator.isValidDouble(portions,
          "A recipe cannot have zero or negative amount of portions!");
  }

  /**
   * Validates user input <code>map</code>. Throws IllegalStateException with message
   * <code>m</code> if <code>map</code> contains no key-value pairs.
   *
   * @param map map with 
   * @param m the message of the thrown Exception
   * @throws IllegalStateException if <code>map</code> is empty
   */
  public static void isValidMap(Map<?, ?> map, String m) throws IllegalStateException {
    if (map == null || map.isEmpty()) {
      throw new IllegalStateException(m);
    }
  }

  /**
   * Validates if user input <code>map</code> contains key <code>k</code>. Throws
   * IllegalStateException with message <code>m</code> if <code>map</code> doesn't contain
   * <code>k</code>.
   * 
   *
   * @param map the Map to be validated
   * @param k key to have its presence validated
   * @param m message of the thrown Exception
   * @throws IllegalStateException if <code>k</code> is not present in <code>map</code>
   */
  public static void validateMapContainsKey(Map<?, ?> map,
                                   String k, 
                                   String m) 
                            throws IllegalStateException {
    if (!map.containsKey(k)) {
      throw new IllegalStateException(m);
    }
  }
}
