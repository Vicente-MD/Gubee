package factory;

import model.Product;
import model.ProductImpl;
import proxy.DynamicProxyProduct;

public class FactoryDynamicProxyProduct implements FactoryProduct {
    @Override
    public Product newProduct() {
        return new DynamicProxyProduct(new ProductImpl());
    }
}
