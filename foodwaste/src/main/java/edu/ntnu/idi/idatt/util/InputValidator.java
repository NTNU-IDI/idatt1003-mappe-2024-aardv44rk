package edu.ntnu.idi.idatt.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class responsible for validating user inputs by making sure they can be parsed to the correct
 * datatypes.
 *
 * @author @aardv44rk
 * @since December 2nd 2024
 * @version 2.0
 */
public class InputValidator {

  protected InputValidator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Prompts the user for a String with prompt {@code p}. 
   * Validates and returns the String if it is not empty.
   *
   * @param sc Scanner that reads user input
   * @param p Prompt that user receives through the terminal
   * @return The string input of the user if it is validated
   * @throws InputMismatchException if user input is blank
   */
  public static String getString(Scanner sc, String p) throws InputMismatchException {
    System.out.println(p);
    boolean valid = false;
    String output = "";
    while (!valid) {
      if (sc.nextLine().isBlank()) {
        throw new InputMismatchException("Blank input not allowed, sorry!");
      } else {
        valid = true;
        output = sc.nextLine();
      }
    }
    return output;
  }

  /**
   * Prompts the user for a String with prompt {@code p}.
   * Validates and returns if it can be parsed to a double.
   *
   * @param sc Scanner that reads user input
   * @param p Prompt that user receives 
   * @return The string input of the user if it is validated
   * @throws InputMismatchException if user input is blank
   */
  public static double getDouble(Scanner sc, String p) throws InputMismatchException {
    System.out.println(p);
    boolean valid = false;
    double output = 0;
    while (!valid) {
      if (sc.nextLine().isBlank()) {
        throw new InputMismatchException("Blank input not allowed, sorry!");
      } else {
        try {
          output = Double.parseDouble(sc.nextLine());
          valid = true;
        } catch (NumberFormatException e) {
          System.out.println("Input has to be a number, whoops!");
        }
      }
    }
    return output;
  }

  /**
   * Prompts the user for a String on a certain format with prompt {@code p}.
   * Validates and returns the String if it is successfully parsed to {@link java.time.LocalDate}.
   *
   * @param sc Scanner that reads user input
   * @param p Prompt that user receives 
   * @return The string input of the user if it is validated
   * @throws IllegalArgumentException if user input does not match the required date format
   */
  public static LocalDate getDate(Scanner sc, String p) throws IllegalArgumentException {
    System.out.println(p);
    boolean valid = false;
    LocalDate output = LocalDate.MIN;
    while (!valid) {
      if (sc.nextLine().isBlank()) {
        throw new IllegalArgumentException("Blank input not allowed, sorry!");
      } else {
        try {
          output = DateUtil.parseDate(sc.nextLine());
          valid = true;
        } catch (DateTimeParseException e) {
          System.out.println("Input has to be a date in the format dd-MM-yyyy, whoops!");
        }
      }
    }      
    return output;
  }
}