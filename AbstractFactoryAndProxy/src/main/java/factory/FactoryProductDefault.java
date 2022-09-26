package factory;

import model.Product;
import model.ProductImpl;

public class FactoryProductDefault implements FactoryProduct {
    public Product instantiateProduct() {
        return new ProductImpl("xxxxxx", "iPhone X", 3500.99);
    }
}
