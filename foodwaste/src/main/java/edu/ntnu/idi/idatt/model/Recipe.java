package edu.ntnu.idi.idatt.model;

import java.util.List;

public class Recipe {
    private final String name;
    private final String description;
    private final String instruction;
    private final List<Ingredient> ingredients; // possibly not a list of ingredients, come back to this TODO

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
}
