package edu.ntnu.idi.idatt.exceptions;

/**
 * This class represents an exception that is thrown when the input provided by the user 
 * is invalid.
 */
public class InvalidInputException extends Exception {

  private static final String ERROR_MESSAGE = "The input you provided is invalid.";

  /**
   * Initializes the exception with default message {@code ERROR_MESSAGE}.
   */
  public InvalidInputException() {
    super(ERROR_MESSAGE);
  }

  /**
   * Initializes the exception with custom message {@code message}.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public InvalidInputException(String message) {
    super(message);
  }
}
