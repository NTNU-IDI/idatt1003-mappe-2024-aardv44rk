package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * FoodStorage class manages the storage and provides methods to...
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

    for (Ingredient i : storage.get(name)) {
      if (
            i.getExpiryDate().equals(ingredient.getExpiryDate()) 
            && i.getPrice() == ingredient.getPrice()
          ) {
        i.setAmount(i.getAmount() + ingredient.getAmount());
        return;
      }
    }
    storage.get(name).add(ingredient);
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
  public int removeIngredient(String name, double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount cannot be negative or zero");
    }
    if (!storage.containsKey(name)) {
      return -1;
    }

    List<Ingredient> ingredients = storage.get(name);
    ingredients.sort(Comparator.comparing(Ingredient::getExpiryDate));
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
      }
    }

    return amount <= 0 ? 0 : 1;
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
  public double getTotalPrice(List<Ingredient> list) {
    return list.stream().mapToDouble(Ingredient::getPrice).sum();
  }
}
