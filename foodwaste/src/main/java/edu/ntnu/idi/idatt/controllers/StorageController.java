package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.model.FoodStorage;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * This class represents the controller for the storage.
 * It is responsible for handling the logic for the storage.
 */
public class StorageController {
  private final FoodStorage fs;

  public StorageController(FoodStorage fs) {
    this.fs = fs;
  }

  /**
   * Method that adds an ingredient to the storage.
   * Calls addIngredient in FoodStorage to handle adding logic.
   *
   * @param ingredient to be put in storage
   */
  public void addIngredient(Ingredient ingredient) {
    ArgumentValidator.isValidObject(ingredient, "Ingredient cannot be null");
    fs.addIngredient(ingredient);
  }

  /**
   * Method that removes an amount of an ingredient from the storage.
   * Calls removeIngredient in FoodStorage to handle removing logic.
   *
   * @param name Name of the ingredient to be removed
   * @param amount Amount of the ingredient to be removed
   */
  public void removeIngredientAmount(String name, double amount) {
    ArgumentValidator.isValidString(name, "Name cannot be null or empty");
    ArgumentValidator.isValidDouble(amount, "Amount cannot be negative or zero");
    ArgumentValidator.validateMapContainsKey(
            fs.getStorage(), 
            name, 
            "Ingredient not found in storage"
    );
    fs.removeIngredient(name, amount);  
  }

  /**
   * Method that removes an ingredient from the storage.
   * Calls removeIngredient in FoodStorage to handle removing logic.
   *
   * @param name Name of the ingredient to be removed
   */
  public void removeIngredient(String name) {
    ArgumentValidator.isValidString(name, "Name cannot be null or empty");
    ArgumentValidator.validateMapContainsKey(
            fs.getStorage(), 
            name, 
            "Ingredient not found in storage"
    );
    fs.removeIngredient(name);
  }

  /**
   * Method that returns a list of all ingredients that have an expirydate before a certain date.
   *
   * @param date chosen date
   * @return A list consisting of all expired <code>Ingredient</code> objects
   */
  public List<Ingredient> getExpiredFood(LocalDate date) {
    ArgumentValidator.isValidDate(date, "Invalid date! Please try again.");
    return fs.getStorage().values().stream()
                    .flatMap(List::stream)
                    .filter(ingredient -> ingredient.getExpiryDate().isBefore(date))
                    .collect(Collectors.toList()); 
  }

  /**
   * Method that returns a TreeMap of all ingredients in the storage.
   *
   * @return A TreeMap consisting of all ingredients in the storage
   */
  public Map<String, List<Ingredient>> sortStorage() {
    return new TreeMap<String, List<Ingredient>>(fs.getStorage());
  }

  
  /**
   * Function to search for an ingredient in the storage.
   *
   * @param name of ingredient to search for
   * @returns A list of all ingredients matching that name, or <code>Collections.emptyList()</code>
   , an immutable list as default if the <code>key</code> name cannot be found in the storage.
   */
  public List<Ingredient> searchIngredient(String name) {
    ArgumentValidator.isValidString(name, "Name cannot be null or empty");
    return fs.getStorage().getOrDefault(name, Collections.emptyList());
  }

  /**
   * Method that returns total price of all ingredients in a <code>List</code>.
   *
   * @param list a List of <code>Ingredient</code> objects
   * @return a double corresponding to the total value of the objects in the list
   */
  public static double getTotalPrice(List<Ingredient> list) {
    ArgumentValidator.isValidObject(list, "List cannot be null, whoops!");
    return list.stream()
          .mapToDouble(Ingredient::getUnitPrice)
          .sum();
  }

  /**
   * Method that sums the total price of all Ingredients in a list.
   *
   * @param list a List of <code>Ingredient</code> objects 
   * @return a double corresponding to the total amount
   */
  public static double getTotalAmount(List<Ingredient> list) {
    ArgumentValidator.isValidObject(list, "List cannot be null, whoops!");
    return list.stream()
          .mapToDouble(ingredient -> ingredient.getAmount())
          .sum();
  }

  /**
   * Checks if the storage contains an ingredient with a certain amount.
   *
   * @param name a String representing name of the ingredient
   * @param amount a double representing amount of the ingredient to check for
   * @return true if the storage contains the ingredient with the given amount, false otherwise
   */
  public boolean containsIngredient(String name, double amount) {
    return fs.getStorage().containsKey(name) && getTotalAmount(fs.getStorage().get(name)) >= amount;
  }
}
