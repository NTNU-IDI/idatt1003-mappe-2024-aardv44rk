package edu.ntnu.idi.idatt.util;

import java.util.Date;

/**
 * Validates inputs.
 */
public class Validator {

  public static boolean isValidString(String input) {
    return !(input == null || input.trim().isEmpty());
  }

  public static boolean isValidDouble(double input) {
    return input > 0;
  }

  public static boolean isValidDate(Date date) {
    return !(date == null);
  }
}
