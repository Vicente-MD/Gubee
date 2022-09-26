package factory;

import model.Product;
import proxy.DynamicProxyProduct;

public class FactoryDynamicProxyProduct implements FactoryProduct {
    @Override
    public Product instantiateProduct() {
        return new DynamicProxyProduct();
    }
}
