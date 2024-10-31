package edu.ntnu.idi.idatt;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FoodStorage {
    HashMap<String, List<Ingredient>> ingredientList;

    public FoodStorage()
    {
        ingredientList = new HashMap<>();
    }

    // TODO: addIngredient method here
    

    public void searchIngredient(Ingredient ingredient)
    {
        {
            int[] i = {1};
            String name = ingredient.getName();
            // Sjekker om navnet finnes, hvis ikke defaulter vi til en immutabel liste
            ingredientList.getOrDefault(name, Collections.emptyList())
            // Printer så ut for hver gjenstand i listen, nummerert 
               .forEach(food -> System.out.println((i[0]++) + ". " + food));    
            // finnes kanskje en lettere måte å gjøre dette på? må testes      
        }
    }

    public void removeIngredient(Ingredient ingredient)
    {
        searchIngredient(ingredient);
        System.out.println("What " + ingredient.getName() + "(s) do you want to take out of the storage?");

        // må legge til måte å velge mat å ta ut: scanner, split
    } 

    // Printer alle elementer av listene i hashmappen
    public void displayStorage()
    {
        ingredientList.values().forEach(list -> list.forEach(System.out::println));
    }
    // få denne til å se litt clean ut 

    public void displayExpiredFoods()
    {
        ingredientList.values().forEach(list -> list.stream().filter(food -> food.getExpiryDate().equals(""))
        .forEach(System.out::println));
    // fikse expiryDate logikken, bruke SimpleDateFormat bl.a.
    // finne måte å sjekke totalpris på objektene som har gått ut på dato    
    }    
    public void getTotalValue()
    {
    }

}   