package edu.ntnu.idi.idatt.model;

import java.util.List;

/**
 * Recipe class. TODO
 */
public class Recipe {
  String name;
  String description;
  String instruction;
  List<Ingredient> ingredients;

  /**
   * Sole constructor.
   *
   * @param name A name for a recipe
   * @param description A description for a recipe
   * @param instruction An instruction for a recipe
   * @param ingredients A list of ingredients
   */ //TODO EXCEPTION HANDLING
  public Recipe(String name, String description, String instruction, List<Ingredient> ingredients) {
    if (ingredients.isEmpty()) {
      throw new IllegalArgumentException("Recipe cannot have 0 ingredients!");
    }
    this.name = name;
    this.description = description;
    this.instruction = instruction;
    this.ingredients = ingredients;
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

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Recipe:\n").append(name).append("\n")
                      .append(description).append("\n");
    ingredients.stream()
              .forEach(ingredient -> sb.append(ingredient).append("\n"));
    sb.append("\nStep by step:").append(instruction);
    return sb.toString();
  }

}
