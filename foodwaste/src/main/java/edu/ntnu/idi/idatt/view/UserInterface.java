package edu.ntnu.idi.idatt.view;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.util.InputHandler;



/**
 * A terminal user interface that manages all methods for printing and receiving input.
 * <p>Works together with <code>InputHandler</code> for input handling
 * and instantiates the storage and cookbook.</p>
 */
public class UserInterface {
  private final FoodStorage foodStorage = new FoodStorage();
  private final Cookbook cookbook = new Cookbook();
  private final Scanner sc = new Scanner(System.in);
  private final InputHandler inputHandler = new InputHandler();

  /**
   * Starts the User Interface. TODO
   */
  public void start() {
    try (sc) {
      boolean running = true;
      int input;

      do {
        displayMenu();
        if (sc.hasNextInt()) { // use inputhandler here
          input = sc.nextInt();
          sc.nextLine();
        } else {
          sc.nextLine();
          throw new InputMismatchException("Invalid input, please try again");
        }
        switch (input) {
          case 1 -> displayStorage();
          case 2 -> displayExpiredFoods();
          case 3 -> ingredientSearcher();
          case 4 -> ingredientRemover();
          case 5 -> foodStorage.getTotalValue(
            foodStorage.getIngredientList().values().stream().flatMap(List::stream).toList()
          ); // TODO
          case 6 -> ingredientAdder();
          case 7 -> readCookbook();
          case 8 -> recipeRecommender();
          case 9 -> {
            System.out.println("Thank you for using the Food Storage app (name temp)");
            running = false;
          }
          default -> throw new InputMismatchException("Error");
        }
      } while (running);
    }
  }

  /**
   * Responsible for initializing the TUI.
   */
  public void init() { // temp init method
    addDummyIngredients();
    System.out.println("Succesfully added dummy ingredients");
  }

  //temp
  /**
   * Temporary, added docs to remove problems in vscode.
   */
  public void addDummyIngredients() {
    foodStorage.addIngredient(new Ingredient("Milk", 2.5, new Date(), 1.0, "liter"));
    foodStorage.addIngredient(new Ingredient("Eggs", 3.0, new Date(), 11, "pieces"));
    foodStorage.addIngredient(new Ingredient("Flour", 1.5, new Date(), 2.0, "kg"));
    foodStorage.addIngredient(new Ingredient("Butter", 4.0, new Date(), 500, "grams"));
    foodStorage.addIngredient(new Ingredient("Sugar", 1.2, new Date(), 1.0, "kg"));
  } 

  /**
   * Displays the choices in the menu. TODO
   */
  public void displayMenu() { // add pages TODO
    System.out.println("""
        ------ Food Storage v0.1 ------
        Welcome. What would you like to do?
        1. Look inside the storage
        2. Check if there are any expired ingredients
        3. Search for a specific ingredient
        4. Take out an ingredient
        5. Check the total value of ingredients
        6. Add an item to the storage
        7. Look inside the cookbook
        8. ?
        9. Exit
        """);
  }

  /**
   * Displays all items in the storage.
   */
  public void displayStorage() {
    System.out.println("Displaying storage contents:");
    
    if (foodStorage.getIngredientList().isEmpty()) {
      System.out.println("No ingredients in storage.");
    } else {
      foodStorage.getIngredientList().values().stream()
          .flatMap(List::stream)
          .forEach(System.out::print);
    }
  }

  /**
   * Displays the food in the storage that have expired and their value.
   * Calls <code>getTotalValue</code> to display the value of the items.
   */
  public void displayExpiredFoods() {
    List<Ingredient> expiredFoods = foodStorage.getExpiredFood(new Date());
    double sum = foodStorage.getTotalValue(expiredFoods);
    System.out.println("Expired items in storage:\n");
    expiredFoods.stream().forEach(System.out::println);
    System.out.println("\nTotal value of expired items: " + sum + " kr.");
  }

  /**
   * Prints all element of the cookbook.
   */
  public void readCookbook() {
    cookbook.getCookbook().stream().forEach(System.out::println);
  }

  /**
   * Recommends a recipe to the user.
   * 
   * <p>Calls <code>recommendRecipes</code> from <code>Cookbook</code>.</p>
   */
  public void recipeRecommender() {
    System.out.println("Here are the recipes you can make: ");
    cookbook.recommendRecipes().stream().forEach(System.out::println);
  }

  /**
   * Searches for an ingredient in the storage.
   * 
   * <p>Calls <code>searchIngredient</code> from <code>FoodStorage</code>.</p>
   *
   * @param scanner Scanner to read input.
   */
  public void ingredientSearcher() {
    String input = inputHandler.getValidString("What ingredient do you want to search for?");
    System.out.println(foodStorage.searchIngredient(input));
  }

  public void ingredientRemover() {
    String name = inputHandler.getValidString("What ingredient do you want remove?");
    double amount = inputHandler.getValidDouble("How many " + name + " do you want to remove?");
    int result = foodStorage.removeIngredient(name, amount);
    switch(result) {
      case -1 -> System.out.println("Ingredient wasn't found in the storage :(");
      case 0 -> System.out.println("Oh no! You didn't have enough to remove the desired amount!");
      case 1 -> System.out.println("Successfully removed " + amount + " of " + name);
      default -> System.out.println("An unknown error has occured.");
    }
  }

  public void ingredientAdder() {
    String name = inputHandler.getValidString("What is the name of the ingredient?");
    double price = inputHandler.getValidDouble("What is the price of the ingredient?");
    Date expiryDate = inputHandler.getValidDate("""
                                                  What date does it expire?
                                                  On the format dd-MM-yyyy please
                                                """);
    double amount = inputHandler.getValidDouble("How many of the ingredient do you want to add?");
    String unit = inputHandler.getValidString("And in what unit is the ingredient measured?");
    foodStorage.addIngredient(new Ingredient(name, price, expiryDate, amount, unit));
    System.out.println("Successfully added " + amount + unit + " of " + name + " to the storage!");
  }
}
