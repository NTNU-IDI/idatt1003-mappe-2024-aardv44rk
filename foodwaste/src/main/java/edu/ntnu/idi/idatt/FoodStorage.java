package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// TODO: replace all comments with javadoc

public class FoodStorage {
    private final HashMap<String, List<Ingredient>> ingredientList;

    // Constructur initiates a new HashMap
    public FoodStorage() {
        ingredientList = new HashMap<>();
    }

    // TODO: add exception-handling

    public void addIngredient(Ingredient ingredient) {
        String name = ingredient.getName();
        ingredientList.putIfAbsent(name, new ArrayList<>());
        ingredientList.get(name).add(ingredient);
    }

    public List<Ingredient> searchIngredient(String name) {
        {
            // Check if the name of the ingredient is in the storage. If not, we return an
            // immutable list.
            return ingredientList.getOrDefault(name, Collections.emptyList());
        }
    }

    public void removeIngredient(String name, Scanner scanner) {
        searchIngredient(name);
        System.out.println("What " + name + "(s) do you want to take out of the storage?");
        
        // TODO: Finish method to remove items
    }

    public void displayStorage() {
        // TODO: comment here (after making clean)
        ingredientList.values().forEach(list -> list.forEach(System.out::println));
    }

    // TODO: Make output look "clean"
    // Maybe separate food into categories, and print by categories?
    // ooor print by keys and amount of each item? that way we dont print 1000 lines
    // at once.
    public void displayExpiredFoods() {
        ingredientList.values()
            .forEach(list -> list.stream()
            .filter(food -> food.getExpiryDate().equals(""))
            .forEach(System.out::println));
        // TODO: fix date-logic
        // TODO: method to get prices of all these and sum (with streams?) maybe call
        // getTotalValue function.
    }

    public void getTotalValue() {
        double totalValue = ingredientList.values().stream()
            .flatMap(List::stream)
            .mapToDouble(Ingredient::getPrice)
            .sum();
        System.out.println("Total value of items: " + totalValue);
    }
}