package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.util.Map;



/**
 * The recipe class is responsible for creating recipes and provides getters,
 * setters and a print method.
 *
 * @author @aardv44rk
 * @since November 27th 2024
 * @version 2.0
 */
public class Recipe {
  String name;
  String description;
  String instruction;
  double portions;
  Map<String, Quantity> ingredientMap;

  /**
   * Sole constructor.
   *
   * @param name A name for a recipe
   * @param description A description for a recipe
   * @param instruction An instruction for a recipe
   * @param ingredientMap A map of ingredient names and amounts
   */
  public Recipe(String name, 
                String description, 
                String instruction,
                Map<String, Quantity> ingredientMap, 
                double portions
  ) {
    ArgumentValidator.isValidRecipe(name, description, instruction, ingredientMap, portions);
    this.name = name;
    this.description = description;
    this.instruction = instruction;
    this.ingredientMap = ingredientMap;
    this.portions = portions;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getInstruction() {
    return instruction;
  }

  public Map<String, Quantity> getIngredientMap() {
    return ingredientMap;
  }

  public double getPortions() {
    return portions;
  }

  // setters
  /**
   * Sets the name of a recipe.
   *
   * @param name the name of the recipe
   * @throws IllegalArgumentException if name field is null or empty
   */
  public final void setName(String name) throws IllegalArgumentException {
    ArgumentValidator.isValidString(name, "Name field cannot be empty!");
    this.name = name;
  }

  /**
   * Sets the description of a recipe.
   *
   * @param description the description of the recipe
   * @throws IllegalArgumentException if description field is null or empty
   */
  public void setDescription(String description) throws IllegalArgumentException {
    ArgumentValidator.isValidString(description, "Description field cannot be empty!");
    this.description = description;
  }

  /**
   * Sets the instruction of a recipe.
   *
   * @param instruction the instruction of the recipe
   * @throws IllegalArgumentException if instruction field is null or empty
   */
  public void setInstruction(String instruction) throws IllegalArgumentException {
    ArgumentValidator.isValidString(instruction, "Instruction field cannot be empty!");
    this.instruction = instruction;
  }

  /**
   * Sets the <code>Ingredients</code> in a recipe.
   *
   * @param ingredientMap the name of the recipe
   * @throws IllegalArgumentException if <code>ingredients</code> is empty
   */
  public void setIngredientMap(Map<String, Quantity> ingredientMap) 
              throws IllegalArgumentException {
    ArgumentValidator.isValidMap(ingredientMap, "Recipe cannot have zero ingredients!");
    this.ingredientMap = ingredientMap;
  }

  /**
   * Sets the <code>portions</code> in a recipe.
   *
   * @param portions the portion amount of the recipe
   * @throws IllegalArgumentException if <code>portions</code> is negative or empty
   */
  public void setPortions(double portions) {
    ArgumentValidator.isValidDouble(portions,
            "Recipe cannot have zero or negative amount of portions!");
    this.portions = portions;
  }

  /**
   * Method that prints the arguments of a <code>Recipe</code> object <code>r</code>.
   *
   * @return a string containing the information of a recipe 
   */
  public String printRecipe() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Recipe:\n").append(this.name).append("\n")
                      .append(this.description).append("\nIngredients:\n");

    ingredientMap.forEach((ingredientName, quantity)
                          -> sb.append("* ").append(ingredientName).append(" ")
                          .append(quantity.quantityString()).append("\n"));
    sb.append("\nStep by step:\n").append(this.instruction);

    return sb.toString();
  }

  /**
   * Compares the ingredients in a recipe to the ingredients in a storage.
   * Returns true or false based on the result.
   *
   * @param fs storage to compare
   */
  public boolean isMakeableRecipe(FoodStorage fs) {
    Map<String, Quantity> ingredients = this.getIngredientMap();
    return ingredients.entrySet().stream()
      .allMatch(i -> {
        String ingredientName = i.getKey().toLowerCase();
        double amount = i.getValue().getAmount();

        double availableAmount = FoodStorage.getTotalAmount(fs.searchIngredient(ingredientName));
        return amount <= availableAmount;
      });
  }
}