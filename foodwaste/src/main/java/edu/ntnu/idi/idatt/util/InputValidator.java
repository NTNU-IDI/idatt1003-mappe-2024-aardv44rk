package edu.ntnu.idi.idatt.util;

import edu.ntnu.idi.idatt.exceptions.InvalidInputException;
import edu.ntnu.idi.idatt.exceptions.UnsupportedFormatException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


// TODO move loops to UI
/**
 * Class responsible for validating user inputs by making sure they can be parsed to the correct
 * datatypes.
 *
 * @author @aardv44rk
 * @since December 2nd 2024
 * @version 2.0
 */
public class InputValidator {

  private static final Scanner sc = new Scanner(System.in);

  /**
   * Constructor that throws an UnsupportedOperationException to prevent instantiation of the class.
   *
   * @throws UnsupportedOperationException if the class is instantiated
   */
  private InputValidator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Prompts the user for a String with prompt {@code p}. 
   * Validates and returns the String if it is not empty.
   *
   * @param p Prompt that user receives through the terminal
   * @return The string input of the user if it is validated
   * @throws InvalidInputException if user input is blank
   */
  public static String getString(String p) throws InvalidInputException {
    System.out.println(p);
    String output = "";
    String input = sc.nextLine();
    if (input.isBlank()) {
      throw new InvalidInputException("Blank input not allowed, sorry!");
    } else {
      output = input;
    }
    return output;
  }

  /**
   * Prompts the user for a String with prompt {@code p}.
   * Validates and returns if it can be parsed to a double.
   *
   * @param p Prompt that user receives 
   * @return The string input of the user if it is validated
   * @throws InvalidInputException if user input is blank
   */
  public static double getDouble(String p) throws InvalidInputException {
    System.out.println(p);
    double output = 0;
    String input = sc.nextLine();
    if (input.isBlank()) {
      throw new InvalidInputException("Blank input not allowed, sorry!");
    } else {
      try {
        output = Double.parseDouble(input);
      } catch (NumberFormatException e) {
        System.out.println("Input has to be a number, whoops!");
      }
    }
    return output;
  }

  /**
   * Prompts the user for a String on a certain format with prompt {@code p}.
   * Validates and returns the String if it is successfully parsed to {@link java.time.LocalDate}.
   *
   * @param p Prompt that user receives 
   * @return The string input of the user if it is validated
   * @throws IllegalArgumentException if user input does not match the required date format
   */
  public static LocalDate getDate(String p) throws UnsupportedFormatException {
    System.out.println(p);
    LocalDate output = LocalDate.MIN;
    String input = sc.nextLine();
    if (input.isBlank()) {
      throw new IllegalArgumentException("Blank input not allowed, sorry!");
    } else {
      try {
        output = DateUtil.parseDate(input);
      } catch (DateTimeParseException e) {
        System.out.println("Input has to be a date on the format dd-MM-yyyy, whoops!");
      }
    }
    return output;
  }
}