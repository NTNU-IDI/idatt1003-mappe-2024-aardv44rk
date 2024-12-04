package edu.ntnu.idi.idatt.exceptions;

/**
 * This class represents an exception that is thrown when the format provided by the user is not
 * supported by the application.
 */
public class UnsupportedFormatException extends Exception {
  
  private static final String ERROR_MESSAGE = 
        "The format provided did not match the expected format.";
  

  /**
   * Initializes the exception with default message {@code ERROR_MESSAGE}.
   */
  public UnsupportedFormatException() {
    super(ERROR_MESSAGE);
  }
  
  /**
   * Initializes the exception with custom message {@code message}.
   *
   * @param message the message to be displayed when the exception is thrown
   */
  public UnsupportedFormatException(String message) {
    super(message);
  }
}
