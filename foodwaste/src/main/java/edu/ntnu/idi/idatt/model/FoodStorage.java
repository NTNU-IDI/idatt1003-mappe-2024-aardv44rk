package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * FoodStorage class is responsible for managing data in a storage.
 *
 * @author @aardv44rk
 * @since November 19th 2024
 * @version 2.0
 */
public class FoodStorage {
  private final Map<String, List<Ingredient>> storage;

  public FoodStorage() {
    storage = new LowerCaseMap<>();
  }

  /**
   * Method that adds an ingredient to the storage.
   * <p>If an ingredient of the same expiryDate already exists, 
   * the amount of that one is increased instead.</p>
   *
   * @param ingredient to be put in storage
   */
  public void addIngredient(Ingredient ingredient) {
    ArgumentValidator.isValidObject(ingredient, "Ingredient cannot be null");
    String name = ingredient.getName();
    storage.putIfAbsent(name, new ArrayList<>());
    List<Ingredient> ingredients = storage.get(name);
    
    int indexByExpiryDate = findIndex(
            ingredients,                     
            ingredient,                                 
            Comparator.comparing(Ingredient::getExpiryDate)
    );

    ingredients.stream()
              .filter(item -> item.getExpiryDate().equals(ingredient.getExpiryDate())
              && item.getUnitPrice() == ingredient.getUnitPrice())
              .findFirst()
              .ifPresentOrElse(item -> mergeIngredient(ingredient, item),
                  () -> ingredients.add(indexByExpiryDate, ingredient));
  }

  /**
   * Finds the correct index for an <code>Ingredient</code> in a list, based on a comparison
   * between target ingredient and ingredients in the list, provided by the Comparator class.
   *
   * @param ingredients the list of ingredients to be compared to
   * @param target the target ingredient to compare against
   * @param comparator the comparator used to compare ingredients
   * @return index where target ingredient is to be placed
   * @throws IllegalArgumentException if any parameters are null
   */
  public int findIndex(List<Ingredient> ingredients, 
                      Ingredient target,
                      Comparator<Ingredient> comparator) {
    ArgumentValidator.isValidObject(ingredients, "List cannot be null");
    ArgumentValidator.isValidObject(target, "Target cannot be null");
    ArgumentValidator.isValidObject(comparator, "Comparator cannot be null");
    return (int) ingredients.stream()
                .takeWhile(ingredient -> comparator.compare(ingredient, target) < 0)
                .count();
  }

  /**
   * Merges two ingredients if their units are the same.
   *
   * @param ingredient new Ingredient to be merged
   * @param other Ingredient already in storage
   */
  public void mergeIngredient(Ingredient ingredient, Ingredient other) {
    double amount = ingredient.getAmount();
    double price = ingredient.getUnitPrice();
    String unit = ingredient.getUnit();
    double otherAmount = other.getAmount();
    double otherPrice = ingredient.getUnitPrice();
    String otherUnit = other.getUnit();

    if (!unit.equalsIgnoreCase(otherUnit)) {
      throw new IllegalArgumentException("Unit differs from previous unit: " + otherUnit); 
    }

    other.setAmount(amount + otherAmount);
    other.setUnitPrice(price + otherPrice);
  }

  /**
   * Removes a double <code>amount</code> from a List corresponding to key <code>name</code>
   * from the storage. Returns true or false based on the result.
   *
   * @param name of ingredient to be removed 
   * @param amount of ingredient to be removed
   * @return true or false based on result 
   * @throws IllegalArgumentException if <code>amount</code> is negative or zero
   * @throws IllegalStateException if key <code>name</code> not present in <code>storage</code>
   */
  public boolean removeIngredient(String name, double amount) throws IllegalArgumentException,
                                  IllegalStateException {
    
    List<Ingredient> ingredients = storage.get(name);
    Iterator<Ingredient> iterator = ingredients.iterator();
    Ingredient ingredient;

    while (iterator.hasNext() && amount > 0) {
      ingredient = iterator.next();
      double ingredientAmount = ingredient.getAmount();
      if (ingredientAmount >= amount) {
        ingredient.setAmount(ingredientAmount - amount);
        amount = 0;
      } else {
        amount -= ingredientAmount;
        iterator.remove();
        if (ingredients.isEmpty()) {
          storage.remove(name);
        }
      }
    }

    return amount <= 0;
  }

  /**
   * Method that removes ingredient(s) completely from the storage.
   *
   * @param name a String corresponding to key <code>name</code> in storage
   * @throws IllegalStateException if storage does not contain <code>name</code>
   */
  public void removeIngredient(String name) {
    storage.remove(name);
  }

  public Map<String, List<Ingredient>> getStorage() {
    return storage;
  }
}
