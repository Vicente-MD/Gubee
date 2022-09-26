package factory;

import model.Product;
import proxy.ProxyProduct;

public class FactoryProxyProduct implements FactoryProduct {

    @Override
    public Product instantiateProduct() {   return new ProxyProduct();  }
}
