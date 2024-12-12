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
  private final String name;
  private final String description;
  private final String instruction;
  private final double portions;
  private final Map<String, Ingredient> ingredientMap;

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
                double portions,
                Map<String, Ingredient> ingredientMap
  ) {
    ArgumentValidator.isValidRecipe(name, description, instruction, ingredientMap, portions);
    this.name = name;
    this.description = description;
    this.instruction = instruction;
    this.portions = portions;
    this.ingredientMap = ingredientMap;
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

  public Map<String, Ingredient> getIngredientMap() {
    return ingredientMap;
  }

  public double getPortions() {
    return portions;
  }

   /**
   * Method that prints the arguments of a <code>Recipe</code> object <code>r</code>.
   *
   * @return a string containing the information of a recipe 
   */
  public String recipeToString() {
    StringBuilder sb = new StringBuilder();
    sb.append(("-").repeat(40)).append("\n")
                      .append(this.name).append("\n")
                      .append(this.description).append("\nIngredients:\n");

    ingredientMap.forEach((ingredientName, ingredient)
                          -> sb.append("* ").append(ingredientName).append(" ")
                          .append(ingredient.getAmount()).append(" ")
                          .append(ingredient.getUnit()).append("\n"));
    sb.append("\nStep by step:\n").append(this.instruction);

    return sb.toString();
  }

}