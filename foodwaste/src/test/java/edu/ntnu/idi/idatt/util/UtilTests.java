package edu.ntnu.idi.idatt.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * UtilTests class in responsible for testing instantiation of utility classes.
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 1.1 
 */
class UtilTests {
  @Test
  void testArgumentValidatorInstantiation() {
    UnsupportedOperationException e = assertThrows(UnsupportedOperationException.class,
                        () -> new ArgumentValidator(),
                        "ArgumentValidator should never be instantiated so should throw exception");
    assertEquals("Utility class", e.getMessage(), "Messages should match");
  }

  @Test
  void testDateUtilInstantiation() {
    UnsupportedOperationException e = assertThrows(UnsupportedOperationException.class,
                        () -> new DateUtil(),
                        "DateUtil should never be instantiated so should throw exception");
    assertEquals("Utility class", e.getMessage(), "Messages should match");
  }
}
