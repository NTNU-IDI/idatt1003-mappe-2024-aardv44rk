package edu.ntnu.idi.idatt.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    return amount < 0 ? 0 : 1;
  }

  public List<Ingredient> searchIngredient(String name) {
    return storage.getOrDefault(name, Collections.emptyList());
  }
}

