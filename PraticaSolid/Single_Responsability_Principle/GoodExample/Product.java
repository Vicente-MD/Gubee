package Single_Responsability_Principle.GoodExample;

// Class has only one responsability
public class Product {
    private String id;
    private String name;
    private double price;
    private double taxRate;

    public Product(String id, String name, double price, double taxRate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.taxRate = taxRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    
}