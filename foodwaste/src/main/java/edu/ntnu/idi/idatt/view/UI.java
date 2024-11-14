package edu.ntnu.idi.idatt.view;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;


public class UI {
  private final FoodStorage foodStorage = new FoodStorage();
  private final Cookbook cookbook = new Cookbook();
  private final Scanner sc = new Scanner(System.in);

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
          throw new InputMismatchException("Invalid input, please try again");
        }
        switch (input) {
          case 1 -> displayStorage();
          case 2 -> displayExpiredFoods();
          // case 3 -> foodStorage.searchIngredient(String name); Add input handling for
          // this ?
          // case 4 -> foodStorage.removeIngredient(String name); and this? or make
          // separate methods in ui...
          case 5 -> foodStorage.getTotalValue();
          // case 6 -> foodStorage.addIngredient(Ingredient ingredient)
          // Idea:
          // One separate method, e.g. menuChoice(int input) instead of a switch case
          // this way it's possible to handle all types of inputs ?? maybe, ask TA
          case 7 -> readCookbook();
          case 9 -> {
            System.out.println("Thank you for using the Food Storage app (name temp)");
            running = false;
          }
          default -> throw new InputMismatchException("Error");
        }
      } while (running);
    }
  }

  public void init() { // temp dummy method
    addDummyIngredients();
    System.out.println(foodStorage);
    System.out.println(foodStorage.sortStorage());
    
  }

  //temp

  public void addDummyIngredients() {
    foodStorage.addIngredient(new Ingredient("Milk", 2.5, new Date(), 1.0, "liter"));
    foodStorage.addIngredient(new Ingredient("Eggs", 3.0, new Date(), 11, "pieces"));
    foodStorage.addIngredient(new Ingredient("Flour", 1.5, new Date(), 2.0, "kg"));
    foodStorage.addIngredient(new Ingredient("Butter", 4.0, new Date(), 500, "grams"));
    foodStorage.addIngredient(new Ingredient("Sugar", 1.2, new Date(), 1.0, "kg"));
  } 

  public void displayMenu() {
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

  public void displayStorage() {
    foodStorage.getIngredientList().values().forEach(list -> list.forEach(System.out::println));
  }

  public void displayExpiredFoods() {
    List<Ingredient> expiredFoods = foodStorage.getExpiredFood(new Date());
    double sum = expiredFoods.stream()
        .mapToDouble(Ingredient::getPrice)
        .peek(System.out::println)
        .sum();

    System.out.println("Total value of expired items: " + sum + " kr.");
    // getTotalValue function.
  }

  public void readCookbook() {
    cookbook.getCookbook().stream().forEach(System.out::println);
  }
}