package edu.ntnu.idi.idatt.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.model.Recipe;


public class UI {
    private FoodStorage foodStorage = new FoodStorage();
    private Cookbook cookbook = new Cookbook();
    private Scanner sc = new Scanner(System.in);
    
    public void start() {
        boolean running = true;
        int input;

        do {
            displayMenu();
            if (sc.hasNextInt()) { // use inputhandler here
                input = sc.nextInt();
                sc.nextLine();
            }
            else {
                throw new InputMismatchException("Invalid input, please try again");
            }
            switch(input) {
                case 1 -> displayStorage();
                case 2 -> displayExpiredFoods();
                // case 3 -> foodStorage.searchIngredient(String name); Add input handling for this ?
                // case 4 -> foodStorage.removeIngredient(String name); and this? or make separate methods in ui...
                case 5 -> foodStorage.getTotalValue();
                // case 6 -> foodStorage.addIngredient(Ingredient ingredient)
                // Idea:
                // One separate method, e.g. menuChoice(int input) instead of a switch case
                // this way it's possible to handle all types of inputs ?? maybe, ask TA
                // case 7 -> 
                case 9 -> {
                    System.out.println("Thank you for using the Food Storage app (name temp)");
                    running = false;
                }
            }
        } while (running);
        
        sc.close();
    }

    public void init() {
        Ingredient ingredient = new Ingredient("melk", 10, null, 0, null);
        List<Ingredient> list = new ArrayList();
        list.add(ingredient);
        Recipe recipe1 = new Recipe("a", "d", "23", list);
        Recipe recipe2 = new Recipe("b", "c", "23", list);
        Recipe recipe3 = new Recipe("c", "b", "23", list);
        Recipe recipe4 = new Recipe("d", "a", "a3", list);

        cookbook.addRecipe(recipe1);
        cookbook.addRecipe(recipe2);
        cookbook.addRecipe(recipe3);
        cookbook.addRecipe(recipe4);
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
                8. 
                9. Exit
                """);
    }
    
    public void displayStorage() {
        foodStorage.getIngredientList().values().forEach(list -> list.forEach(System.out::println)); // needs fixing
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
