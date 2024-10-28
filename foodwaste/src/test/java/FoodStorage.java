
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class FoodStorage {
    HashMap<String, List<Ingredient>> ingredientList;

    public FoodStorage()
    {
        ingredientList = new HashMap<>();
    }
    void addIngredient(Ingredient ingredient)
    {
        // Sjekker om det finnes en ingrediens av samme navn i hashmappet allerede
        if (!ingredientList.containsKey(ingredient.getName())) 
        // Lager en ny key og oppretter en hashmap for å lagre og differensiere objekter av samme navn / type
        {
            ingredientList.put(ingredient.getName(), new ArrayList<>());       
        }

        ingredientList.get(ingredient.getName()).add(ingredient);
    }

    public void searchIngredient(Ingredient ingredient)
    {
        {

        }
    }

    
}
