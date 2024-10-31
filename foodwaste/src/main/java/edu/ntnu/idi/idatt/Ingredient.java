package edu.ntnu.idi.idatt;

public class Ingredient 
{
    private double price;   // Price is a double to be able to store both integers and floats
    private String expiryDate; // Expiry dates are stored as strings for easy formatting with "..."
    private final String name;    // Names are stored as strings for obvious reasons. Names are used to differentiate ingredients.
    private double amount; // Amount is stored as double to allow quantities of 2.0L, 2L, 0.6L all at the same time.
    private String unit;

    // The ingredient class stores the name, price, expiry date, and amount of an Ingredient object.
    public Ingredient(String name, double price, String expiryDate, int amount, String unit)
    {
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.unit = unit;
    }

    // The only function of the createIngredient method is to initialize new ingredient types.
    Ingredient createIngredient(String name) 
    {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        return new Ingredient(name, 0, "0", 0, "");
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