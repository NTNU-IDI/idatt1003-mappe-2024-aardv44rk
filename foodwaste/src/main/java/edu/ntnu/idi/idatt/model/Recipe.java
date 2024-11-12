package edu.ntnu.idi.idatt.model;

import java.util.List;

public class Recipe {
    private String name;
    private String description;
    private String instruction;
    private List<Ingredient> ingredients; // possibly not a list of ingredients, come back to this TODO

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
