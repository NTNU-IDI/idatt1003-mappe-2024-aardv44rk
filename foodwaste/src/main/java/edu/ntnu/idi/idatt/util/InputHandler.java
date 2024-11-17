package edu.ntnu.idi.idatt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Validates user inputs. If user input is incorrect it catches an exception and informs the user.
 */
public class InputHandler {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * Prompts the user for an input, checks if input is a valid double.
   *
   *
   * @param userPrompt String to prompt the user in the terminal
   * @return the user input parsed to double
   * @throws IllegalArgumentException if user input is negative
   */
  public double getValidDouble(String userPrompt) {
    double value = 0;
    boolean valid = false;

    while (!valid) {
      System.out.println(userPrompt);
      String input = scanner.nextLine();
      try {
        value = Double.parseDouble(input);
        valid = true;
        if (value < 0) {
          valid = false;
          throw new IllegalArgumentException("Please enter a positive number :)"); //TODO ?
        }
      } catch (NumberFormatException e) {
        System.out.println("""
                          I'm sorry, that's not a number!
                          Please enter a number :)
                          """);
      } catch (NullPointerException e) {
        System.out.println("I'm sorry, input cannot be empty. (" + e.getMessage() + ")");
      }
    }
    return value;
  }
  
  /**
   * Prompts the user in the terminal. Returns user input if it is not empty.
   *
   * @param userPrompt String to prompt the user in the terminal 
   * @return the users input
   */
  public String getValidString(String userPrompt) {
    boolean valid = false;
    String output = "";
    while (!valid) {
      System.out.println(userPrompt);
      if (scanner.hasNextLine()) {
        output = scanner.nextLine();
        valid = true;
      }
    }
    return output;
  }

  /**
   * Prompts the user in the terminal. Returns the users input if it can be parsed
   * as an integer and is positive.
   *
   * @param userPrompt String to prompt the user in the terminal
   * @return the users input
   */
  public int getValidInt(String userPrompt) {
    int value = 0;
    boolean valid = false;

    while (!valid) {
      System.out.println(userPrompt);
      String input = scanner.nextLine();

      try {
        value = Integer.parseInt(input);
        valid = true;
        if (value < 0) {
          valid = false;
          System.out.println("That cannot be a negative number, sorry.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter an integer");
      } 
    }

    return value;
  }

  /**
   * Maybe not needed so temp.
   */
  public double getValidFloat(String userPrompt) { // maybe not needed
    double value = 0;

    return value;
  }

  /**
   * Prompts the user in the terminal for a date. Returns the users input if
   * it is on the format dd-MM-yyyy
   *
   * @param userPrompt String to prompt the user in the terminal
   * @return the users input
   */
  public Date getValidDate(String userPrompt) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    sdf.setLenient(false);

    Date date = null;
    Boolean valid = false;

    while (!valid) {
      System.out.println(userPrompt);
      String input = scanner.nextLine();

      try {
        date = sdf.parse(input);
        valid = true;
      } catch (ParseException e) {
        System.out.println("Please enter a date on the format dd-MM-yyyy. :)");
      }
    }
    return date;
  }
}
