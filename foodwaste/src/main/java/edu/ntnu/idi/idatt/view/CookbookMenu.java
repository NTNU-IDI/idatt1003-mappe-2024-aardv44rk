package edu.ntnu.idi.idatt.view;

/**
 * This class represents the interface for the cookbook view.
 */
public class CookbookMenu implements UserInterface {

  /**
   * Displays the cookbook menu.
   */
  @Override
  public void display() {
    // Print interface
  }

  /**
   * Handles the input provided by the user.
   *
   * @param input the input provided by the user
   */
  public void handleInput(String input) {
    switch (input) {
      case "1" -> addRecipe();
      case "2" -> removeRecipe();
      default -> System.out.println("Invalid input");
    }
  }

  public void addRecipe() {

  }

  public void removeRecipe() {

  }
}
