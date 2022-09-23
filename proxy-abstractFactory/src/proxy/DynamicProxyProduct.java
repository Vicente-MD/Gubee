package proxy;


import app.Main;
import factory.ServiceFactory;
import model.Product;
import service.Service;

import java.lang.reflect.Proxy;

public class DynamicProxyProduct implements Service {
    private Service productService = ServiceFactory.getService();

    private final Service productDynamicProxy = (Service) Proxy.newProxyInstance(
            Main.class.getClassLoader(),
            new Class[]{Service.class},
            new ProductInvocationHandler(productService)
    );

    public void create(Product p) {
        productDynamicProxy.create(p);
    }

}
