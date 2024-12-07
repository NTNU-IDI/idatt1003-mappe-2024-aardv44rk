package edu.ntnu.idi.idatt.view;

import edu.ntnu.idi.idatt.controllers.CookbookController;
import edu.ntnu.idi.idatt.controllers.StorageController;
import edu.ntnu.idi.idatt.exceptions.InvalidInputException;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.model.LowerCaseMap;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.util.InputValidator;
import edu.ntnu.idi.idatt.util.PrintUtil;
import java.util.List;
import java.util.Map;

/**
 * This class represents the interface for the cookbook view.
 */
public class CookbookMenu implements UserInterface {
  
  private final StorageController storageController;
  private final CookbookController cookbookController;

  public CookbookMenu(StorageController storageController, CookbookController cookbookController) {
    this.storageController = storageController;
    this.cookbookController = cookbookController;
  }

  /**
   * Displays the cookbook menu.
   */
  @Override
  public void display() {
    System.out.println(("-").repeat(40));
    System.out.println("Cookbook Menu");
    System.out.println("1. Add recipe");
    System.out.println("2. Remove recipe");
    System.out.println("3. View cookbook");
    System.out.println("4. Check makeable recipes");
    System.out.println("5. Recipe recommender");
    System.out.println("6. Go Back");
    System.out.println(("-").repeat(40));

    try {
      String input = InputValidator.getString("Enter your choice: ");
      handleInput(input);
    } catch (InvalidInputException e) {
      System.out.println(e.getMessage());
    }
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
      case "3" -> viewCookbook();
      case "4" -> checkMakeableRecipes();
      case "5" -> recipeRecommender();
      case "6" -> new MainMenu(storageController, cookbookController).display();
      default -> System.out.println("Invalid input");
    }
  }

  /**
   * Reads user input and adds a recipe to the cookbook.
   */
  public void addRecipe() {
    boolean running = true;
    while (running) {
      try {
        String name = InputValidator.getString("Enter the name of the recipe: ");
        String description = InputValidator.getString("Enter the description of the recipe: ");
        String instruction = InputValidator.getString("Enter the instruction of the recipe: ");
        double portions = InputValidator.getPositiveDouble("Enter the number of portions: ");
        System.out.println("Enter the ingredients of the recipe: ");
        Map<String, Ingredient> ingredientMap = new LowerCaseMap<>();
        boolean addingIngredients = true;
        while (addingIngredients) {
          String ingredientName = InputValidator.getString("Enter the name of the ingredient: ");
          double amount = InputValidator.getPositiveDouble("Enter the amount of the ingredient: ");
          String unit = InputValidator.getString("Enter the unit of the ingredient: ");
          Ingredient ingredient = new Ingredient(ingredientName, amount, unit);
          ingredientMap.put(ingredientName, ingredient);
          String answer = InputValidator.getString("Do you want to add more ingredients? (y/n): ");
          if (answer.equalsIgnoreCase("n")) {
            addingIngredients = false;
          }
        }
        Recipe recipe = new Recipe(name, description, instruction, portions, ingredientMap);
        cookbookController.addRecipe(recipe);
        running = false;
      } catch (InvalidInputException | IllegalArgumentException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("An unknown error occurred: " + e.getMessage());
      }
    }
  }

  /**
   * Reads user input and removes a recipe from Cookbook {@code cookbook}.
   */
  public void removeRecipe() {
    boolean running = true;
    while (running) {
      try {
        String name = InputValidator.getString("Enter the name of the recipe you want to remove: ");
        cookbookController.removeRecipe(name);
        running = false;
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        System.out.println("An unknown error occurred: " + e.getMessage());
      }
    }
  }

  /**
   * Displays all recipes in Cookbook {@code cookbook} to the user.
   */
  public void viewCookbook() {
    try {
      System.out.println("All recipes in the cookbook: ");
      Map<String, Recipe> recipes = cookbookController.getRecipes();
      if (recipes.isEmpty()) {
        System.out.println("No recipes in the cookbook");
        return;
      }
      String header = String.format("%-20s %-50s", "Name", "Description");
      String line = "-".repeat(header.length());
      System.out.println(header);
      System.out.println(line);
      PrintUtil.printMap(recipes, recipe -> cookbookController.recipeToMapString(recipe));
      System.out.println(line);
      System.out.println("Do you want to view a recipe? (y/n)");
      String answer = InputValidator.getString("Enter your choice: ");
      if (answer.equalsIgnoreCase("y")) {
        String recipeName = InputValidator.getString("Enter the name of the recipe: ");
        viewRecipe(recipeName);
      } else if (!answer.equalsIgnoreCase("n")) {
        System.out.println("Invalid input");
      } 
    } catch (Exception e) {
      System.out.println("An unknown error occurred: " + e.getMessage());
    }
  }

  /**
   * Displays a recipe to the user.
   *
   * @param name the name of the recipe to display
   */
  public void viewRecipe(String name) {
    Recipe recipe = cookbookController.getRecipes().get(name);
    if (recipe == null) {
      System.out.println("Recipe not found");
      return;
    }
    System.out.println(recipe.recipeToString());
  }

  public void checkMakeableRecipes() {
    System.out.println("Recipes currently makeable: ");
    PrintUtil.printList(cookbookController.getMakeableRecipes(), Recipe::recipeToString);
  }

  /**
   * Recommends a recipe based on the ingredients in FoodStorage {@code fs}.
   */
  public void recipeRecommender() {
    System.out.println("Recommended recipe: ");
    List<Recipe> recipes = cookbookController.recommendRecipes();
    PrintUtil.printList(recipes, Recipe::recipeToString);
  }
}
