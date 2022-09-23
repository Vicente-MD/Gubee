package factory;

import proxy.DynamicProxyProduct;
import proxy.ProxyProduct;

public class ProxyFactory implements Factory {

    public ProxyProduct createProxyProduct() {
        return new ProxyProduct();
    }

    public DynamicProxyProduct createDynamicProxyProduct() {
        return new DynamicProxyProduct();
    }

}
