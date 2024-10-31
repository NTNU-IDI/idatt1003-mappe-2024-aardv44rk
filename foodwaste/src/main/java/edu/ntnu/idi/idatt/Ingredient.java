package edu.ntnu.idi.idatt;

public class Ingredient 
{
    private final String name;    // Names are stored as strings for obvious reasons. Names are used to differentiate ingredients.
    private double price;   // Price is a double to be able to store both integers and floats
    private String expiryDate; // Expiry dates are stored as strings for easy formatting with "..."
    private double amount; // Amount is stored as double to allow quantities of 2.0L, 2L, 0.6L all at the same time.
    private String unit;

    // The ingredient class stores the name, price, expiry date, and amount of an Ingredient object.
    public Ingredient(String name, double price, String expiryDate, double amount, String unit)
    {
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient(String name) {
        this.name = name;
        this.price = 0;
        this.expiryDate = "";
        this.amount = 0;
        this.unit = "";
    }   
    
    // TODO: 
    // The only function of the createIngredient method is to initialize new ingredient types.
    // Ingredient createIngredient(String name, double price, String expiryDate, double amount, String unit) 
    // {
    //     if (name.isEmpty()) {
    //         throw new IllegalArgumentException("Name cannot be empty.");
    //     }
    //     if (price <= 0) {
    //         throw new IllegalArgumentException("Price cannot be less than 1.");
    //     }
        
    //     // TODO: date handling

    //     if (amount < 0) {
    //         throw new IllegalArgumentException("Amount cannot be negative");
    //     }

    //     return new Ingredient(name, price, expiryDate, amount, unit);
    // }

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

    public double getAmount()
    {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    // setters
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name)
        .append("\n Price: ").append(price)
        .append("\n Amount: ").append(amount)
        .append("\n Expiry Date: ").append(expiryDate);
        return sb.toString(); // run toString on StringBuilder object to return correct datatype
    }
}