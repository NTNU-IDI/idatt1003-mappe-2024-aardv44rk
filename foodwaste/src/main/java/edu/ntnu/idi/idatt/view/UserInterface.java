package edu.ntnu.idi.idatt.view;

/**
 * This interface represents a user interface that can be displayed and interacted with.
 */
public interface UserInterface {
  void display();

  void handleInput(String input);
}