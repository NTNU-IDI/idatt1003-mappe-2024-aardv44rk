public class Ingredient 
{
    double price;
    String expiryDate;
    String name;

    public Ingredient(String name, double price, String expiryDate)
    {
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    Ingredient createIngredient(String name, double price, String expiryDate)
    {
        return new Ingredient(name, price, expiryDate);
    }

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
}
