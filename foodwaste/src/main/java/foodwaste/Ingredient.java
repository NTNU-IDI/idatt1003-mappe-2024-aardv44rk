package foodwaste;
public class Ingredient 
{
    double price;
    String expiryDate;
    String name;
    String amount;
    

    public Ingredient(String name, double price, String expiryDate, String amount)
    {
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.amount = amount;
    }

    Ingredient createIngredient(String name, double price, String expiryDate)
    {
        return new Ingredient(name, price, expiryDate, "");
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