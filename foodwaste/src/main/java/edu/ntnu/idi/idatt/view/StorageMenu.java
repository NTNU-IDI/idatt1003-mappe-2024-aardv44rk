package edu.ntnu.idi.idatt.view;

/**
 * This class represents the interface for the storage view.
 */
public class StorageMenu implements UserInterface {
  @Override
  public void display() {
    // Print interface
  }

  /**
   * Handles the input provided by the user.
   */
  public void handleInput(String input) {
    switch (input) {
      case "1" -> addIngredient();
      case "2" -> removeIngredient();
      default -> System.out.println("Invalid input");
    }
  }

  public void addIngredient() {

  }

  public void removeIngredient() {

  }
}