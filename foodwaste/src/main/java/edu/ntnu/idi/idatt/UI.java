package edu.ntnu.idi.idatt;

import java.util.List;

public class UI {
    FoodStorage foodStorage = new FoodStorage();

    public void start() {
    }

    public void init() {

    }

    public void displayStorage() {
        foodStorage.getIngredientList().values().forEach(list -> list.forEach(System.out::println));
    }

    public void displayExpiredFoods() {
        List<Ingredient> expiredFoods = foodStorage.getExpiredFood();
        double sum = expiredFoods.stream()
            .mapToDouble(Ingredient::getPrice)
            .peek(System.out::println)
            .sum();

        System.out.println("Total value of expired items: " + sum + " kr.");
        // TODO: fix date-logic
        // getTotalValue function.
    }
}
