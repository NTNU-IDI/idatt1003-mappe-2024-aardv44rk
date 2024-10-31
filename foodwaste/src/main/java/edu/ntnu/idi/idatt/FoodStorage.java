package edu.ntnu.idi.idatt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// TODO: replace all comments with javadoc

public class FoodStorage {
    HashMap<String, List<Ingredient>> ingredientList;
    
    // Constructur initiates a new HashMap
    public FoodStorage() {
        ingredientList = new HashMap<>();
    }

    // TODO: hva er poenget med denne? spørre studass
    // add exception-handling
    public Ingredient createNewIngredient(String name) {
        ingredientList.putIfAbsent(name, new ArrayList<>());
        return new Ingredient(name);
    }

    public void addIngredient(String name, double price, String expiryDate, int amount, String unit) {
        Ingredient ingredient = createNewIngredient(name);
        ingredient.setPrice(price);
        ingredient.setExpiryDate(expiryDate);
        ingredient.setAmount(amount);
        ingredient.setUnit(unit);
        ingredientList.get(name).add(ingredient);
    }

    public void searchIngredient(Ingredient ingredient) {
        {
            int[] i = {1};
            String name = ingredient.getName();
            // Check if the name of the ingredient is in the storage. If not, we return an immutable list.
            ingredientList.getOrDefault(name, Collections.emptyList())
            // Then we print a numbered list of each food matching that name
               .forEach(food -> System.out.println((i[0]++) + ". " + food));    
            // TODO: easier way to do this? (numbering)  
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        searchIngredient(ingredient);
        System.out.println("What " + ingredient.getName() + "(s) do you want to take out of the storage?");

        // TODO: Finish method to remove items
    } 

    public void displayStorage() {
        // TODO: comment here (after making clean)
        ingredientList.values().forEach(list -> list.forEach(System.out::println));
    }
        // TODO: Make output look "clean"
        // Maybe separate food into categories, and print by categories?
        // ooor print by keys and amount of each item? that way we dont print 1000 lines at once.
    public void displayExpiredFoods() {
        ingredientList.values().forEach(list -> list.stream().filter(food -> food.getExpiryDate().equals(""))
        .forEach(System.out::println));
        // TODO: fix date-logic
        // TODO: method to get prices of all these and sum (with streams?) maybe call getTotalValue function.
    }    
    public void getTotalValue() {
        double totalValue = ingredientList.values().stream().flatMap(List::stream).mapToDouble(Ingredient::getPrice).sum();
        System.out.println("Total value of items: " + totalValue);
    }
}   