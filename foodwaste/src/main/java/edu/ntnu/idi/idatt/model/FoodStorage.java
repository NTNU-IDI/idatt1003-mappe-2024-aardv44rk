package edu.ntnu.idi.idatt.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The FoodStorage class manages a collection of ingredients.
 * 
 * <p>The class stores ingredients in a HashMap and provides methods to add, search, remove 
 * and sort the ingredients. It also provides the user with the ability to see what ingredients 
 * are expired and the total value of items in the collection.</p>
 */
public class FoodStorage {
  private final Map<String, List<Ingredient>> ingredientList;

  // Constructur initiates a new HashMap
  public FoodStorage() {
    ingredientList = new HashMap<>();
  }


  /**
   * Adds an ingredient to the collection.
   *
   * <p>The <code>addIngredient</code> method adds an <code>Ingredient</code> to a list in the
   * <code>HashMap</code>, using the name as the key. 
   * 
   * <p>If there exists an ingredient with the same expiry date, 
   * the amount of that one is increased instead</p>
   *
   * @param ingredient An ingredient object.
   */
  public void addIngredient(Ingredient ingredient) {
    String name = ingredient.getName();
    ingredientList.putIfAbsent(name, new ArrayList<>());

    List<Ingredient> ingredients = ingredientList.get(name);
    ingredients.add(ingredient);
    Iterator<Ingredient> iterator = ingredients.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getExpiryDate() == ingredient.getExpiryDate()) {
        iterator.next().setAmount(iterator.next().getAmount()
                                  + ingredient.getAmount());
        return;
      } 
    }
  }

  /**
   * Searches for a specific ingredient in the storage.
   * 
   * <p>The <code>searchIngredient</code> function takes the name of an <code>Ingredient</code>
   * as input and returns a <code>List</code> if the ingredient is present.
   * Returns an immutable list from <code>Collections</code> if not.</p>
   *
   * @param name The name of the desired ingredient.
   * @return List&lt;Ingredient&gt; A list of <code>Ingredients</code>
   */
  public List<Ingredient> searchIngredient(String name) {
    return ingredientList.getOrDefault(name, Collections.emptyList());
  }

  /** 
   * The <code>removeIngredient</code> method takes a <code>name</code> and <code>amount</code>
   * as input and removes that amount of an <code>Ingredient</code> from the storage.
   * 
   * <p>If the desired amount is successfully removed 1 is returned.</p>
   * 
   * <p>If the desired amount is not succesfully removed 0 is returend </p>
   * 
   * <p>If the ingredient was not in the storage to begin with -1 is returned</p>
   *
   * @param name The name of the ingredient to remove.
   * @param amount The amount of the ingredient to remove.
   * @return An integer corresponding to the result.
   */
  public int removeIngredient(String name, double amount) {
    if (!ingredientList.containsKey(name)) {
      return -1;
    }

    List<Ingredient> ingredients = ingredientList.get(name);
    Iterator<Ingredient> iterator = ingredients.iterator();

    while (iterator.hasNext() && amount > 0) { // sort by expirydate
      Ingredient ingredient = iterator.next();
      if (ingredient.getAmount() >= amount) {
        ingredient.setAmount(ingredient.getAmount() - amount);
        amount = 0;
        if (ingredient.getAmount() == 0) {
          iterator.remove();
          return 1;
        }
      } else {
        amount -= ingredient.getAmount();
        iterator.remove();
      }
    }
    if (amount > 0) {
      return 0;
    }
    return -1;
  }

  /**
   * Uses <code>TreeMap</code> to sort <code>keys</code> in storage alphabetically.
   *
   * @return <code>Map&lt;String, List&lt;Ingredient&gt;&gt;</code> sortedMap
   *        a <code>TreeMap</code> implementation of the ingredientList.
   */
  public Map<String, List<Ingredient>> sortStorage() {
    Map<String, List<Ingredient>> sortedMap = new TreeMap<>();
    sortedMap.putAll(ingredientList);
    return sortedMap;
  }

  public Map<String, List<Ingredient>> getIngredientList() {
    return ingredientList;
  }

  /**
   * Returns expired food.
   * 
   */
  public List<Ingredient> getExpiredFood(Date date) { 
    // maybe just use new Date(), input not rly needed
    List<Ingredient> expiredFood = ingredientList.values()
        .stream().flatMap(List::stream)
        .filter(ingredient -> ingredient.getExpiryDate().before(date))
        .collect(Collectors.toList()); //Collectors.toList?? toList??
    return expiredFood;
  }

  /**
   * Returns total price of all objects in a <code>Collection&lt;Ingredient&gt;</code>.
   *
   * @param ingredientCollection A collection of ingredient objects
   * @return The total price of ingredients in the Collection 
   */
  public double getTotalValue(Collection<Ingredient> ingredientCollection) { 
    double totalValue = ingredientCollection.stream()
        .mapToDouble(Ingredient::getPrice)
        .sum();
    return totalValue;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    ingredientList.values().stream()
                  .flatMap(List::stream)
                  .forEach(ingredient -> sb.append(ingredient).append("\n"));
    return sb.toString();
  }
}