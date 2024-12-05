package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.exceptions.InvalidInputException;
import edu.ntnu.idi.idatt.exceptions.UnsupportedFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

  /**
   * Constructor that throws an UnsupportedOperationException to prevent instantiation of the class.
   *
   * @throws UnsupportedOperationException if the class is instantiated
   */
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
   * @throws InvalidInputException if user input is blank
   */
  public static String getString(Scanner sc, String p) throws InvalidInputException {
    System.out.println(p);
    boolean valid = false;
    String output = "";
    String input = sc.nextLine();
    while (!valid) {
      if (input.isBlank()) {
        throw new InvalidInputException("Blank input not allowed, sorry!");
      } else {
        valid = true;
        output = input;
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
   * @throws InvalidInputException if user input is blank
   */
  public static double getDouble(Scanner sc, String p) throws InvalidInputException {
    System.out.println(p);
    boolean valid = false;
    double output = 0;
    String input = sc.nextLine();
    while (!valid) {
      if (input.isBlank()) {
        throw new InvalidInputException("Blank input not allowed, sorry!");
      } else {
        try {
          output = Double.parseDouble(input);
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
  public static LocalDate getDate(Scanner sc, String p) throws UnsupportedFormatException {
    System.out.println(p);
    boolean valid = false;
    LocalDate output = LocalDate.MIN;
    String input = sc.nextLine();
    while (!valid) {
      if (input.isBlank()) {
        throw new IllegalArgumentException("Blank input not allowed, sorry!");
      } else {
        try {
          output = DateUtil.parseDate(input);
          valid = true;
        } catch (DateTimeParseException e) {
          System.out.println("Input has to be a date in the format dd-MM-yyyy, whoops!");
        }
      }
    }      
    return output;
  }
}