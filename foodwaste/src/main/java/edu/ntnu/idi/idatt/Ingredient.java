package edu.ntnu.idi.idatt;

public class Ingredient 
{
    double price;   // Price is a double to be able to store both integers and floats
    String expiryDate; // Expiry dates are stored as strings for easy formatting with "..."
    String name;    // Names are stored as strings for obvious reasons. Names are used to differentiate ingredients.
    String amount; // Amount is stored as strings to accommodate units (L, kg, and so on) and amounts at the same time.
    // Amount logic is subject to change
    
    public Ingredient(String name, double price, String expiryDate, String amount)
    {
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.amount = amount;
    }

    // The only function of the createIngredient method is to initialize new ingredient types.
    Ingredient createIngredient(String name) 
    {
        return new Ingredient(name, 0, "0", "");
    }

    // getters
    public String getName() 
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public String getExpiryDate()
    {
        return expiryDate;
    }

    public String getAmount()
    {
        return amount;
    }

    // setters
    public void setAmount(String amount) {
        this.amount = amount;
    }
}