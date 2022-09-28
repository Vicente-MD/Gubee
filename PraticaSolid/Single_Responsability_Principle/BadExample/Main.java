package Single_Responsability_Principle.BadExample;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("xxxxx", "iPhone X", 3599.00, 1.14);

        System.out.println("Product:\n"+"\tid: "+product.getId()+"\n\tName: "+product.getName()+"\n\tPrice: "+product.calculateTax());

    }
}
