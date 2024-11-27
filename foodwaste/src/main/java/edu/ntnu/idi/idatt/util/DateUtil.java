package edu.ntnu.idi.idatt.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateUtil is a utility class responsible for parsing and formatting dates.
 * <p>Provides methods for parsing and formatting with a default and custom format.</p>
 */
public class DateUtil {

  /**
   * Constructor that throws Exception to prevent instantiation.
   *
   * @throws UnsupportedOperationException if DateUtil is instantiated
   */
  protected DateUtil() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Utility class");
  }

  private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";

  // Parses date
  public static LocalDate parseDate(String dateString, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return LocalDate.parse(dateString, formatter);
  }

  public static LocalDate parseDate(String dateString) {
    return parseDate(dateString, DEFAULT_DATE_FORMAT);
  }

  // Formats date
  public static String formatDate(LocalDate date, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return date.format(formatter);
  }

  public static String formatDate(LocalDate date) {
    return formatDate(date, DEFAULT_DATE_FORMAT);
  }


}
