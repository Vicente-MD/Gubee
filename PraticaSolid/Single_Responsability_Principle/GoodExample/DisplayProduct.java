package Single_Responsability_Principle.GoodExample;

public class DisplayProduct {
    public String display(String name, double price){
        return "Product: " + name + ", price (no tax): " + price;
    }
}
