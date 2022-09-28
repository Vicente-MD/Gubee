package factory;

import model.Product;
import model.ProductImpl;
import proxy.ProxyProduct;

public class FactoryProxyProduct implements FactoryProduct {

    @Override
    public Product newProduct() {   return new ProxyProduct(new ProductImpl());  }
}
