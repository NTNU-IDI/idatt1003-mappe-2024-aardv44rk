package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: replace all comments with javadoc
public class FoodStorage {
    private final Map<String, List<Ingredient>> ingredientList;

    // Constructur initiates a new HashMap 
    public FoodStorage() {
        ingredientList = new HashMap<>();
    }

    // TODO: add exception-handling
    public void addIngredient(Ingredient ingredient) {
        String name = ingredient.getName();
        List<Ingredient> ingredients = ingredientList.get(name);
        // Iterator<Ingredient> iterator = ingredients.iterator();
        ingredientList.putIfAbsent(name, new ArrayList<>());

        // while (iterator.hasNext()) {
        //     if (iterator.next().getExpiryDate() == ingredient.getExpiryDate()) {
        //         iterator.next().setAmount(iterator.next().getAmount() + ingredient.getAmount());
        //         return;
        //     } 
        // }

        // for (Ingredient i : ingredients) {
        //     if (i.getExpiryDate() == ingredient.getExpiryDate()) {
        //         i.setAmount(i.getAmount() + ingredient.getAmount());
        //         return;
        //     }
        // }

        // TODO: choose between these two loops

        ingredients.add(ingredient);
    }

    public List<Ingredient> searchIngredient(String name) {
        // Check if the name of the ingredient is in the storage. If not, we return an
        // immutable list.
        return ingredientList.getOrDefault(name, Collections.emptyList());
    }

    public int removeIngredient(String name, double amount) {
        if (!ingredientList.containsKey(name)) {
            return -1;
        }

        List<Ingredient> ingredients = ingredientList.get(name);
        Iterator<Ingredient> iterator = ingredients.iterator();

        while (iterator.hasNext() && amount > 0) {
            Ingredient ingredient = iterator.next();
            if (ingredient.getAmount() >= amount) {
                ingredient.setAmount(ingredient.getAmount() - amount);
                amount = 0;
                if (ingredient.getAmount() == 0) {
                    iterator.remove();
                    return 1; 
                }
            }
            else {
            amount -= ingredient.getAmount();
            iterator.remove(); // same as above
            }
        }
        if (amount > 0) {
            return 0;
        }
        return -1;
    }

    public List<String> sortStorage() {
        List<String> sortedStorage = new ArrayList<>(ingredientList.keySet());
        sortedStorage.sort(String::compareToIgnoreCase); 
        return sortedStorage; // temp, might need to return all ingredients objects aswell?
    }

    // Map instead of HashMap here to code to an interface directly
    public Map<String, List<Ingredient>> getIngredientList() {
        return ingredientList;
    }

    public List<Ingredient> getExpiredFood(Date date) { // maybe just use new Date(), input not rly needed
        List<Ingredient> expiredFood = ingredientList.values()
                                .stream().flatMap(List::stream)
                                .filter(ingredient -> ingredient.getExpiryDate().before(date))
                                .collect(Collectors.toList());
        return expiredFood;
    }

    public double getTotalValue() { // might need an input here to use this function on display expired food
        double totalValue = ingredientList.values().stream()
            .flatMap(List::stream)
            .mapToDouble(Ingredient::getPrice)
            .sum();
        return totalValue;
    }
}