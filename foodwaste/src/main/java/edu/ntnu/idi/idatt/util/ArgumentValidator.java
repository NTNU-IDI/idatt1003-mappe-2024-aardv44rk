package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.model.Ingredient;
import java.time.LocalDate;
import java.util.List;



/**
 * Class responsible for handling all input validation in the program. 
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 1.1
 */

public class ArgumentValidator {
  private static boolean isRecipe = false;

  public static void setIsRecipe(boolean isRecipe) {
    ArgumentValidator.isRecipe = isRecipe;
  }

  protected ArgumentValidator() {
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
   * Validates user input <code>date</code>. Throws IllegalArgumentException 
   * with message <code>m</code> if <code>date</code> is null, else does nothing.
   *
   * @param date the <code>Date</code> to be validated
   * @param m the message of the thrown exception
   * @throws IllegalArgumentException if <code>date</code> is empty or null
   */
  public static void isValidDate(LocalDate date, String m) throws IllegalArgumentException {
    if (date == null) {
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
   * Validates user input <code>list</code>. Throws IllegalStateException with message
   * <code>m</code> if <code>list</code> is empty or null, else does nothing. 
   *
   * @param list the list to be validated
   * @param m the message of the thrown Exception
   * @throws IllegalStateException if <code>list</code> is null or empty
   */
  public static void isValidList(List<Ingredient> list, String m) throws IllegalStateException {
    if (list == null || (isRecipe && list.isEmpty())) {
      throw new IllegalArgumentException(m);
    }
  }
}
