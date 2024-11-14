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
   * Gets a valid double.
   *
   * @param userPrompt The userPrompt to prompt the user.
   * @return double
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
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number");
      }
    }
    return value;
  }

  public String getValidString(String userPrompt) {
    boolean valid = false;
    String output = "";
    while (!valid) {
      System.out.println(userPrompt);
      String input = scanner.nextLine();
      
      if (input.isBlank()) {
        throw new IllegalArgumentException("Input cannot be empty");
      }
      else {
        output = input;
        break;
      }
    }
    return output;
  }

  public int getValidInt(String userPrompt) {
    int value = 0;
    boolean valid = false;

    while (!valid) {
      System.out.println(userPrompt);
      String input = scanner.nextLine();

      try {
        value = Integer.parseInt(input);
        valid = true;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter an integer");
      }
    }

    return value;
  }

  public double getValidFloat(String userPrompt) { // maybe not needed
    double value = 0;

    return value;
  }

  public Date getValidDate(String userPrompt) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    sdf.setLenient(false);

    Date date = null;
    Boolean valid = false;

    while (!valid) {
      System.out.println(userPrompt);
      String input = scanner.nextLine();

      try {
        sdf.parse(input);
        valid = true;
      } catch (ParseException e) {
        System.out.println("Please enter a date on the format dd-MM-yyyy. :)");
      }
    }
    return date;
  }
}
