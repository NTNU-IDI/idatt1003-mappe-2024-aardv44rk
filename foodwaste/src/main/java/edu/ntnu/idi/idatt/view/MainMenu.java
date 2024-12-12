package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.controllers.CookbookController;
import edu.ntnu.idi.idatt.controllers.StorageController;
import edu.ntnu.idi.idatt.exceptions.InvalidInputException;
import edu.ntnu.idi.idatt.util.DummyValues;
import edu.ntnu.idi.idatt.util.InputValidator;

/**
 * This class represents the main menu of the application.
 */
public class MainMenu implements UserInterface {
  private static boolean initialized = false;
  private final StorageController storageController;
  private final CookbookController cookbookController;

  public MainMenu(StorageController storageController, CookbookController cookbookController) {
    this.storageController = storageController;
    this.cookbookController = cookbookController;
  }
  
  /**
   * Displays the main menu of the application.
   */
  @Override
  public void display() {
    if (!initialized) {
      try {
        String answer = InputValidator.getString("Do you want to load dummy values? (y/n): ");
        if (answer.equalsIgnoreCase("y")) {
          DummyValues.initializeDummyValues(storageController, cookbookController);
        }
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage());
      }
      initialized = true;
    }
    boolean running = true;
    while (running) {
      System.out.println("\nWelcome to the food waste application!\n");
      System.out.println(("-").repeat(40));
      System.out.println("1. Storage");
      System.out.println("2. Cookbook");
      System.out.println("3. Exit");
      System.out.println("-".repeat(40));

      try {
        String input = InputValidator.getString("Enter your choice: ");
        handleInput(input);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Handles the input provided by the user.
   *
   * @param input the input provided by the user
   */
  @Override
  public void handleInput(String input) {
    switch (input) {
      case "1" -> new StorageMenu(storageController, cookbookController).display();
      case "2" -> new CookbookMenu(storageController, cookbookController).display();
      case "3" -> System.exit(0);
      default -> System.out.println("Invalid input");
    }
  }
}
