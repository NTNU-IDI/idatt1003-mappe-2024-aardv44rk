package edu.ntnu.idi.idatt;

import java.util.Date;
import java.util.List;

public class UI {
    FoodStorage foodStorage = new FoodStorage();
    
    public void start() {
    }

    public void init() {
    }

    public void displayMenu() {
        
        System.out.println("""
                ------ Food Storage v0.1 ------
                
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
