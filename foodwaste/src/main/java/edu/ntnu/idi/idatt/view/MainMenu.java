package edu.ntnu.idi.idatt.view;

/**
 * This class represents the main menu of the application.
 */
public class MainMenu implements UserInterface {
  
  /**
   * Displays the main menu of the application.
   */
  @Override
  public void display() {
    System.out.println("Welcome to the food waste application!\n");
    System.out.println("1. Storage");
    System.out.println("2. Cookbook");
    System.out.println("3. Exit");
  }

  /**
   * Handles the input provided by the user.
   *
   * @param input the input provided by the user
   */
  @Override
  public void handleInput(String input) {
    switch (input) {
      case "1" -> new StorageMenu().display();
      case "2" -> new CookbookMenu().display();
      case "3" -> System.exit(0);
      default -> System.out.println("Invalid input");
    }
  }
}
