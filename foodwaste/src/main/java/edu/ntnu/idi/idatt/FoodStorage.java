package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: replace all comments with javadoc

public class FoodStorage {
    private final Map<String, List<Ingredient>> ingredientList;

    // Constructur initiates a new HashMap (looser coupling this way)
    public FoodStorage() {
        ingredientList = new HashMap<>();
    }

    // TODO: add exception-handling

    public void addIngredient(Ingredient ingredient) {
        String name = ingredient.getName();
        ingredientList.putIfAbsent(name, new ArrayList<>()); // looser coupling with arraylist here
        ingredientList.get(name).add(ingredient);
    }

    public List<Ingredient> searchIngredient(String name) {
        {
            // Check if the name of the ingredient is in the storage. If not, we return an
            // immutable list.
            return ingredientList.getOrDefault(name, Collections.emptyList());
        }
    }

    public void removeIngredient(String name, double amount) {        
        // TODO: Finish method to remove items
    }

    // Map instead of HashMap here to code to an interface directly
    public Map<String, List<Ingredient>> getIngredientList() {
        return ingredientList;
    }

    public List<Ingredient> getExpiredFood() {
        List<Ingredient> expiredFood = new ArrayList<>();
        ingredientList.values()
            .forEach(list -> list.stream()
            .filter(food -> food.getExpiryDate().before(new Date()))
            .forEach(expiredFood::add));
        return expiredFood;
    }


    public double getTotalValue() {
        double totalValue = ingredientList.values().stream()
            .flatMap(List::stream)
            .mapToDouble(Ingredient::getPrice)
            .sum();
        return totalValue;
    }
}