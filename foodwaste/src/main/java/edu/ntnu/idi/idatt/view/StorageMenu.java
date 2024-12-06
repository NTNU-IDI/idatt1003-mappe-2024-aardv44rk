package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.exceptions.InvalidInputException;
import edu.ntnu.idi.idatt.exceptions.UnsupportedFormatException;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.util.InputValidator;
import edu.ntnu.idi.idatt.util.PrintUtil;
import java.time.LocalDate;
import java.util.List;

/**
 * This class represents the interface for the storage view.
 */
public class StorageMenu implements UserInterface {
  private final FoodStorage fs;

  public StorageMenu(FoodStorage fs) {
    this.fs = fs;
  }

  @Override
  public void display() {
    System.out.println("Storage Menu");
    System.out.println("1. Add ingredient");
    System.out.println("2. Remove ingredient");
    System.out.println("3. Remove amount");
    System.out.println("4. View storage");
    System.out.println("5. Search for ingredient");
    System.out.println("6. View expired");
    System.out.println("7. Go Back"); 
  }

  /**
   * Handles the input provided by the user.
   */
  public void handleInput(String input) {
    switch (input) {
      case "1" -> addIngredient();
      case "2" -> removeIngredient();
      case "3" -> removeAmount();
      case "4" -> viewStorage();
      case "5" -> searchForIngredient();
      case "6" -> viewExpired();
      // case "7" -> new MainMenu().display(); TODO: Fix this
      default -> System.out.println("Invalid choice");
    }
  }

  /**
   * Reads user input and adds an ingredient to a FoodStorage {@code fs}.
   */
  public void addIngredient() {
    boolean running = true;
    while (running) {
      try {
        String name = InputValidator.getString("Enter the name of the ingredient: ");
        double price = InputValidator.getDouble("Enter the price of the ingredient: ");
        LocalDate expiryDate = InputValidator.getDate("Enter the expiry date of the ingredient "
            + "(on format dd-MM-yyyy): ");
        double amount = InputValidator.getDouble("Enter the amount of the ingredient: ");
        String unit = InputValidator.getString("Enter the unit of the ingredient (mL, g, or pcs):");
        Ingredient ingredient = new Ingredient(name, price, expiryDate, amount, unit);
        fs.addIngredient(ingredient);
        running = false;
      } catch (UnsupportedFormatException | InvalidInputException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("An unknown error occurred: " + e.getMessage());
      }
    }
    
  }

  /**
   * Reads user input and removes an ingredient from a FoodStorage {@code fs}.
   */
  public void removeIngredient() {
    boolean running = true;
    while (running) {
      try {
        String name = InputValidator.getString("Enter the name of the ingredient to remove: ");
        fs.removeIngredient(name);
        running = false;
      } catch (InvalidInputException | IllegalStateException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Reads user input and removes an amount of an ingredient from FoodStorage
   * {@code fs}.
   */
  public void removeAmount() {
    boolean running = true;
    while (running) {
      try {
        String name = InputValidator.getString("Enter the name of the ingredient you "
            + "want to remove an amount of: ");
        double amount = InputValidator.getDouble("Enter the amount you want to remove of " 
              + name + ": ");
        fs.removeIngredient(name, amount);
        running = false;
      } catch (InvalidInputException | IllegalArgumentException | IllegalStateException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("An unknown error occurred: " + e.getMessage());
      }
    }
  }

  /**
   * Displays all items in FoodStorage {@code fs}.
   */
  public void viewStorage() {
    List<Ingredient> ingredients = fs.getStorage().values().stream()
        .<Ingredient>flatMap(List::stream)
        .toList();
    PrintUtil.printList(ingredients, Ingredient::ingredientToString);
  }

  /**
   * Reads user input and searches for an ingredient in FoodStorage {@code fs}.
   */
  public void searchForIngredient() {
    boolean running = true;
    while (running) {
      try {
        String name = InputValidator.getString("Enter the name of the ingredient "
            + "you want to search for: ");
        PrintUtil.printList(fs.searchIngredient(name), Ingredient::ingredientToString); 
        running = false;
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("An unknown error occurred: " + e.getMessage());
      }
    }
  }

  /**
   * Reads user input and displays all items that have expired on a given date
   * in FoodStorage {@code fs}.
   */
  public void viewExpired() {
    boolean running = true;
    while (running) {
      try {
        LocalDate date = InputValidator.getDate("Enter the date you want to check for expired "
            + "items (on format dd-MM-yyyy): ");
        PrintUtil.printList(fs.getExpiredFood(date), Ingredient::ingredientToString);
        running = false;
      } catch (UnsupportedFormatException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("An unknown error occurred: " + e.getMessage());
      }
    }
  }
}