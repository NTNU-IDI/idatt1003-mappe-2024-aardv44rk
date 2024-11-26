package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * FoodStorage class manages the storage and provides methods to...
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 0.8
 */
public class FoodStorage {
  private final Map<String, List<Ingredient>> storage;

  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * Method that adds an ingredient to the storage.
   * <p>If an ingredient of the same expiryDate already exists, 
   * the amount of that one is increased instead.</p>
   *
   * @param ingredient to be put in storage
   */
  public void addIngredient(Ingredient ingredient) {
    String name = ingredient.getName();
    storage.putIfAbsent(name, new ArrayList<>());
    List<Ingredient> ingredients = storage.get(name);
    
    int index = (int) ingredients.stream()
              .takeWhile(item -> item.getExpiryDate().compareTo(ingredient.getExpiryDate()) < 0)
              .count();

    ingredients.stream()
              .filter(item -> item.getExpiryDate().equals(ingredient.getExpiryDate())
              && item.getPrice() == ingredient.getPrice())
              .findFirst()
              .ifPresentOrElse(item -> item.setAmount(item.getAmount() + ingredient.getAmount()),
                  () -> ingredients.add(index, ingredient));
  }

  /**
   * Removes a certain amount of an <code>Ingredient</code> from the storage.
   * <p>Returns -1 if the operation failed, 0 if the amount was not successfully removed,
   *  and 1 if it was a success.</p>
   *
   * @param name of ingredient to be removed 
   * @param amount of ingredient to be removed
   * @return an integer based on the result
   */
  public boolean removeIngredient(String name, double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount to remove cannot be negative or zero");
    }
    if (!storage.containsKey(name)) {
      throw new IllegalArgumentException("Ingredient not in storage");
    }

    List<Ingredient> ingredients = storage.get(name);
    Iterator<Ingredient> iterator = ingredients.iterator();
    Ingredient ingredient;

    while (iterator.hasNext() && amount > 0) {
      ingredient = iterator.next();
      if (ingredient.getAmount() >= amount) {
        ingredient.setAmount(ingredient.getAmount() - amount);
        amount = 0;
      } else {
        amount -= ingredient.getAmount();
        iterator.remove();
        if (ingredients.isEmpty()) {
          storage.remove(name);
        }
      }
    }

    return amount <= 0;
  }

  /**
   * Function to search for an ingredient in the storage.
   *
   * @param name of ingredient to search for
   * @returns A list of all ingredients matching that name, or <code>Collections.emptyList()</code>
   , an immutable list as default if the <code>key</code> name cannot be found in the storage.
   */
  public List<Ingredient> searchIngredient(String name) {
    return storage.getOrDefault(name, Collections.emptyList());
  }

  /**
   * Function that sorts the storage by placing all keys and values into a <code>TreeMap</code>. 
   *
   * @return <code>TreeMap</code> that contains same key-value pairs as <code>storage</code>
   */
  public Map<String, List<Ingredient>> sortStorage(Map<String, List<Ingredient>> map) {
    Map<String, List<Ingredient>> sortedStorage = new TreeMap<>();
    sortedStorage.putAll(map);
    return sortedStorage;
  }

  /**
   * Method that returns a list of all ingredients that have an expirydate before a certain date.
   *
   * @param date chosen date
   * @return A list consisting of all expired <code>Ingredient</code> objects
   */
  public List<Ingredient> getExpiredFood(Date date) {
    return storage.values().stream()
                    .flatMap(List::stream)
                    .filter(ingredient -> ingredient.getExpiryDate().before(date))
                    .collect(Collectors.toList()); 
  }

  /**
   * Method that returns total price of all ingredients in a <code>List</code>.
   *
   * @param list a list of <code>Ingredient</code> objects
   * @return a double corresponding to the total value of the objects in the list
   */
  public static double getTotalPrice(List<Ingredient> list) {
    return list.stream()
          .mapToDouble(Ingredient::getPrice)
          .sum() * getTotalAmount(list);
  }

  /**
   * Method that sums the total price of all Ingredients in a list.
   *
   * @param list a List of <code>Ingredient</code> objects 
   * @return a double corresponding to the total amount
   */
  public static double getTotalAmount(List<Ingredient> list) {
    return list.stream()
          .mapToDouble(Ingredient::getAmount)
          .sum();
  }

  public boolean containsIngredient(String name, double amount) {
    return storage.containsKey(name) && getTotalAmount(storage.get(name)) >= amount;
  }

  public Map<String, List<Ingredient>> getStorage() {
    return storage;
  }
}
