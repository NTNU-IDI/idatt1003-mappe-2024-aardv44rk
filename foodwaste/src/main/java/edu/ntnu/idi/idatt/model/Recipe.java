package edu.ntnu.idi.idatt.model;

import java.util.List;

public class Recipe {
    String name;
    String description;
    String instruction;
    List <Ingredient> ingredients;

    public Recipe(String name, String description, String instruction, List<Ingredient> ingredients) {
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
    public String toString() { // TODO
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(description).append(instruction).append(ingredients);
        return sb.toString();
    }

}
