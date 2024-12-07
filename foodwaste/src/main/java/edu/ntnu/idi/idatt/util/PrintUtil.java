package edu.ntnu.idi.idatt.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Class responsible for converting objects to strings and printing them.
 */
public class PrintUtil {
  private PrintUtil() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Prints the objects in a List {@code list} using the function {@code mapper} to convert the
   * object to a string.
   *
   * @param <T> the type of the objects in the list
   * @param list the list to be printed
   * @param mapper the function that converts the object to a string
   */
  public static <T> void printList(List<T> list, Function<T, String> mapper) {
    list.stream()
        .map(mapper)
        .forEach(System.out::println);
  }

  /**
   * Prints the values in a Map {@code map} using the function {@code mapper} to convert the object
   * to a string. 
   *
   * @param <T> the type of the values in the map
   * @param map the map to be printed
   * @param mapper the function that converts the object to a string
   */
  public static <T> void printMap(Map<String, T> map, Function<T, String> mapper) {
    map.values().stream()
                .map(mapper)
                .forEach(System.out::println);
  }
}
