
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FoodStorage {
    HashMap<String, List<Ingredient>> ingredientList;

    public FoodStorage()
    {
        ingredientList = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient)
    {
        // Sjekker om det finnes en ingrediens av samme navn i hashmappet allerede
        if (!ingredientList.containsKey(ingredient.getName())) 
        // Lager en ny key og oppretter en hashmap for å lagre og differensiere objekter av samme navn / type
        {
            ingredientList.put(ingredient.getName(), new ArrayList<>());       
        }
        // Legger til ingrediensen 
        ingredientList.get(ingredient.getName()).add(ingredient);
    }

    public void searchIngredient(Ingredient ingredient)
    {
        {
            String name = ingredient.getName();

            // Dersom ingrediensen eksisterer i ingrediense                                                                                                                                                                                                                                                                                                                                                                                                                                                                       listen printer vi alle elementer i den tilhørende arraylisten
            if (ingredientList.containsKey(name))
            {
                for (Ingredient food : ingredientList.get(name)) {
                    System.out.println(food + "\n");
                }
            }
            // replace med streams eller lambda om mulig
            else
            {
                System.out.println(Collections.emptyList());
            }
        }
    }
}
