package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.model.LowerCaseMap;
import edu.ntnu.idi.idatt.model.Quantity;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.InputValidator;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Class responsible for outputting information to the user. Serves as the TUI
 * of this application.
 *
 * @author @aardv44rk
 * @since December 2nd 2024
 * @version 2.0
 */
public class UserInterface {

  /**
   * Initializes the UserInterface and necessary objects.
   */
  public void init() {
    Scanner sc = new Scanner(System.in);
    FoodStorage fs = new FoodStorage();
    Cookbook cookbook = new Cookbook();
    boolean running = true;
    do {
      start(sc, fs, cookbook);
    } while (running);
  }

  /**
   * Starts the UserInterface and displays the main menu.
   *
   * @param sc       Scanner object used to read input from the user
   * @param fs       FoodStorage object used to store food items
   * @param cookbook Cookbook object used to store recipes
   */
  public void start(Scanner sc, FoodStorage fs, Cookbook cookbook) {
    System.out.println("Booting up the FoodStorage..."); // TODO
    displayMainMenu();
    int choice = sc.nextInt();
    sc.nextLine();
    switch (choice) {
      case 1 -> displayStorageMenu();
      case 2 -> displayCookbookMenu();
      case 3 -> {
        System.out.println("Shutting down the FoodStorage...");
        System.exit(0);
      }
      default -> System.out.println("Invalid choice");
    }
    if (choice == 1) {
      choice = sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1 -> addIngredient(sc, fs);
        case 2 -> removeIngredient(sc, fs);
        case 3 -> removeAmount(sc, fs);
        case 4 -> viewStorage(fs);
        case 5 -> searchForIngredient(sc, fs);
        case 6 -> viewExpired(sc, fs);
        default -> System.out.println("Invalid choice");
      }
    } else if (choice == 2) {
      choice = sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1 -> addRecipe(sc, cookbook);
        case 2 -> removeRecipe(sc, cookbook);
        case 3 -> viewCookbook(cookbook);
        case 4 -> checkMakeableRecipes(cookbook, fs);
        case 5 -> recipeRecommender(cookbook, fs);
        default -> System.out.println("Invalid choice");
      }
    }
  }

  /**
   * Displays the main menu of the application.
   */
  public void displayMainMenu() {
    for (int i = 0; i < 30; i++) {
      System.out.println();
    }
    System.out.println("Welcome to the FoodWaste application!");
    for (int i = 0; i < 30; i++) {
      System.out.print("-");
    }
    System.out.println("\n1. Storage\n2. Cookbook\n3. Quit\nPlease enter your choice:");
  }

  /**
   * Displays the menu of the storage.
   */
  public void displayStorageMenu() {
    for (int i = 0; i < 30; i++) {
      System.out.println();
    }
    System.out.println(
        """
            Storage
            --------------||--------------
            1. Add food item to storage
            2. Remove food item from storage
            3. Remove an amount of a food item
            4. View food storage
            5. Search for food item
            6. View expired food items
            --------------||--------------
            """);
  }

  /**
   * Displays the menu of the cookbook.
   */
  public void displayCookbookMenu() {
    for (int i = 0; i < 30; i++) {
      System.out.println();
    }
    System.out.println(
        """
            Cookbook
            --------------||--------------
            1. Add recipe to cookbook
            2. Remove recipe from cookbook
            3. View cookbook
            4. Check makeable recipes
            5. Recipe recommender
            --------------||--------------
            """);
  }

  /**
   * Reads user input and adds an ingredient to FoodStorage {@code fs}.
   *
   * @param sc Scanner object used to read input from the user
   * @param fs FoodStorage object used to store food items
   */
  public void addIngredient(Scanner sc, FoodStorage fs) {
    try {
      String name = InputValidator.getString(sc,
          "Enter the name of the ingredient: ");
      double price = InputValidator.getDouble(sc,
          "Enter the price of the ingredient: ");
      LocalDate expiryDate = InputValidator.getDate(sc,
          "Enter the expiry date of the ingredient: ");
      double amount = InputValidator.getDouble(sc,
          "Enter the amount of the ingredient: ");
      String unit = InputValidator.getString(sc,
          "Enter the unit of the ingredient: ");
      Quantity quantity = new Quantity(amount, unit);
      Ingredient ingredient = new Ingredient(name, price, expiryDate, quantity);
      fs.addIngredient(ingredient);
    } catch (IllegalArgumentException | InputMismatchException e) {
      System.out.println(e.getMessage());
      addIngredient(sc, fs);
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  /**
   * Reads user input and removes an ingredient from FoodStorage {@code fs}.
   *
   * @param sc Scanner object used to read input from the user
   * @param fs FoodStorage object used to store food items
   */
  public void removeIngredient(Scanner sc, FoodStorage fs) {
    try {
      String name = InputValidator.getString(sc,
          "Enter the name of the ingredient you want to remove: ");
      fs.removeIngredient(name);
    } catch (InputMismatchException e) {
      System.out.println(e.getMessage());
      removeIngredient(sc, fs);
    }
  }

  /**
   * Reads user input and removes an amount of an ingredient from FoodStorage
   * {@code fs}.
   *
   * @param sc Scanner object used to read input from the user
   * @param fs FoodStorage object used to store food items
   */
  public void removeAmount(Scanner sc, FoodStorage fs) {
    try {
      String name = InputValidator.getString(sc,
          "Enter the name of the ingredient you want to remove an amount of: ");
      double amount = InputValidator.getDouble(sc,
          "Enter the amount you want to remove: ");
      fs.removeIngredient(name, amount);
    } catch (InputMismatchException e) {
      System.out.println(e.getMessage());
      removeAmount(sc, fs);
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  public void viewStorage(FoodStorage fs) {
    System.out.println(fs); // TODO
  }

  /**
   * Reads user input and searches for an ingredient in FoodStorage {@code fs}.
   *
   * @param sc Scanner object used to read input from the user
   * @param fs FoodStorage object used to store food items
   */
  public void searchForIngredient(Scanner sc, FoodStorage fs) {
    try {
      String name = InputValidator.getString(sc,
          "Enter the name of the ingredient you want to search for: ");
      System.out.println(fs.searchIngredient(name)); // TODO
    } catch (InputMismatchException e) {
      System.out.println(e.getMessage());
      searchForIngredient(sc, fs);
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  /**
   * Reads user input and displays all items that have expired on a given date
   * in FoodStorage {@code fs}.
   *
   * @param sc Scanner object used to read input from the user
   * @param fs FoodStorage object used to store food items
   */
  public void viewExpired(Scanner sc, FoodStorage fs) {
    try {
      LocalDate date = InputValidator.getDate(sc,
          "Enter the date you want to check for expired food: ");
      System.out.println(fs.getExpiredFood(date)); // TODO
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      viewExpired(sc, fs);
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  /**
   * Reads user input and adds a recipe to Cookbook {@code cookbook}.
   *
   * @param sc       Scanner object used to read input from the user
   * @param cookbook Cookbook object used to store recipes
   */
  public void addRecipe(Scanner sc, Cookbook cookbook) {
    try {
      String name = InputValidator.getString(sc,
          "Enter the name of the recipe: ");
      String description = InputValidator.getString(sc,
          "Enter the description of the recipe: ");
      String instruction = InputValidator.getString(sc,
          "Enter the instruction of the recipe: ");
      double portions = InputValidator.getDouble(sc,
          "Enter the number of portions: ");
      System.out.println("Enter the ingredients of the recipe: ");
      Map<String, Quantity> ingredientMap = new LowerCaseMap<>();
      boolean addingIngredients = true;
      while (addingIngredients) {
        String ingredientName = InputValidator.getString(sc,
            "Enter the name of the ingredient: ");
        double amount = InputValidator.getDouble(sc,
            "Enter the amount of the ingredient: ");
        String unit = InputValidator.getString(sc,
            "Enter the unit of the ingredient: ");
        Quantity quantity = new Quantity(amount, unit);
        ingredientMap.put(ingredientName, quantity);
        System.out.println("Do you want to add more ingredients? (y/n)");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("n")) {
          addingIngredients = false;
        }
      }
      Recipe recipe = new Recipe(name, description, instruction, portions, ingredientMap);
      cookbook.addRecipe(recipe);
    } catch (InputMismatchException e) {
      System.out.println(e.getMessage());
      addRecipe(sc, cookbook);
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  /**
   * Reads user input and removes a recipe from Cookbook {@code cookbook}.
   *
   * @param sc       Scanner object used to read input from the user
   * @param cookbook Cookbook object used to store recipes
   */
  public void removeRecipe(Scanner sc, Cookbook cookbook) {
    try {
      String name = InputValidator.getString(sc,
          "Enter the name of the recipe you want to remove: ");
      cookbook.removeRecipe(name);
    } catch (InputMismatchException e) {
      System.out.println(e.getMessage());
      removeRecipe(sc, cookbook);
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  public void viewCookbook(Cookbook cookbook) {
    System.out.println(cookbook); // TODO
  }

  public void checkMakeableRecipes(Cookbook cookbook, FoodStorage fs) {
    System.out.println(cookbook.recommendRecipes(fs)); // TODO
  }

  public void recipeRecommender(Cookbook cookbook, FoodStorage fs) {
    System.out.println(cookbook.recommendRecipes(fs)); // TODO
  }

}
