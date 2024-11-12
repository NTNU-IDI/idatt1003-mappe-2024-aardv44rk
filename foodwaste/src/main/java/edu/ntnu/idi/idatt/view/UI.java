package edu.ntnu.idi.idatt.view;

import java.util.Date;
import java.util.List;

import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;

public class UI {
    FoodStorage foodStorage = new FoodStorage();
    
    public void start() {
    }

    public void init() {
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
                """);
    }
    
    public void displayStorage() {
        foodStorage.getIngredientList().values().forEach(list -> list.forEach(System.out::println));
    }

    public void displayExpiredFoods(Date date) {
        List<Ingredient> expiredFoods = foodStorage.getExpiredFood(date);
        double sum = expiredFoods.stream()
            .mapToDouble(Ingredient::getPrice)
            .peek(System.out::println)
            .sum();

        System.out.println("Total value of expired items: " + sum + " kr.");
        // getTotalValue function.
    }
}
