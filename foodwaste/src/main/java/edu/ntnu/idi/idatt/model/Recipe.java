package edu.ntnu.idi.idatt.model;

import edu.ntnu.idi.idatt.util.ArgumentValidator;
import java.util.List;


/**
 * The recipe class is responsible for creating recipes and provides getters,
 * setters and a print method.
 *
 * @author @aardv44rk
 * @since November 27th 2024
 * @version 1.1
 */
public class Recipe {
  String name;
  String description;
  String instruction;
  List<Ingredient> ingredients;
  double portions;

  /**
   * Sole constructor.
   *
   * @param name A name for a recipe
   * @param description A description for a recipe
   * @param instruction An instruction for a recipe
   * @param ingredients A list of ingredients
   */
  public Recipe(String name, String description, String instruction,
                List<Ingredient> ingredients, double portions) {
    ArgumentValidator.isValidString(name, "Name cannot be empty!");
    ArgumentValidator.isValidString(description, "Description cannot be empty!");
    ArgumentValidator.isValidString(instruction, "Instruction cannot be empty!");
    ArgumentValidator.isValidList(ingredients, "A recipe cannot have zero ingredients!");
    ArgumentValidator.isValidDouble(portions,
          "A recipe cannot have zero or negative amount of portions!");
    this.name = name;
    this.description = description;
    this.instruction = instruction;
    this.ingredients = ingredients;
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

  public List<Ingredient> getIngredients() {
    return ingredients;
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
   * @param ingredients the name of the recipe
   * @throws IllegalArgumentException if <code>ingredients</code> is empty
   */
  public void setIngredients(List<Ingredient> ingredients) throws IllegalArgumentException {
    ArgumentValidator.setIsRecipe(true);
    ArgumentValidator.isValidList(ingredients, "Recipe cannot have zero ingredients!");
    this.ingredients = ingredients;
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
                      .append(this.description).append("\n");
    this.ingredients.stream()
              .forEach(ingredient -> sb.append("* ").append(ingredient.printRecipeIngredient())
              .append("\n"));
    sb.append("\nStep by step:\n").append(this.instruction);
    return sb.toString();
  }
}