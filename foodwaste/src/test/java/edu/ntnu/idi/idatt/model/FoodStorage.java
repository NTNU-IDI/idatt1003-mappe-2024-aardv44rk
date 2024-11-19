package edu.ntnu.idi.idatt.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * FoodStorage class manages the storage and provides methods to...
 */
public class FoodStorage {
  private Map<String, List<Ingredient>> storage;

  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * Removes a certain amount of an <code>Ingredient</code> from the storage.
   *
   * @param name of ingredient to be removed 
   * @param amount of ingredient to be removed
   * @return an integer based on the result
   */
  public int removeIngredient(String name, double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount cannot be negative");
    }
    if (!storage.containsKey(name)) {
      return -1;
    }
    List<Ingredient> ingredients = storage.get(name);
    Iterator<Ingredient> iterator = ingredients.iterator();

    while (iterator.hasNext() && amount > 0) {
      Ingredient ingredient = iterator.next();
      if (ingredient.getAmount() >= amount) {
        ingredient.setAmount(ingredient.getAmount() - amount);
        if (ingredient.getAmount() == 0) {
          iterator.remove();
        }
      } else {
        ingredient.setAmount(ingredient.getAmount() - amount);
        amount = 0;
      }
    }

    return amount == 0 ? 0 : 1;
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
   * Function that sorts the storage by placing all keys and values into a TreeMap. 
   *
   * @return <code>TreeMap</code> that contains same key-value pairs as <code>storage</code>
   */
  public Map<String, List<Ingredient>> sortStorage() {
    Map<String, List<Ingredient>> sortedStorage = new TreeMap<>();
    sortedStorage.putAll(storage);
    return sortedStorage;
  }
}

