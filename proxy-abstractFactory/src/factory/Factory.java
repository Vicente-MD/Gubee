package factory;

import proxy.DynamicProxyProduct;
import proxy.ProxyProduct;

public interface Factory {
    ProxyProduct createProxyProduct();
    DynamicProxyProduct createDynamicProxyProduct();
}
