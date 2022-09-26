package factory;

import model.Product;
import model.ProductImpl;

public class FactoryProductDefault implements FactoryProduct {
    public Product newProduct() {
        return new ProductImpl();
    }
}
