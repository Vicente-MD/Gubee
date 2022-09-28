package proxy;

import app.Main;
import model.Product;

import java.lang.reflect.Proxy;

public class DynamicProxyProduct implements Product {
    private final Product product;

    private final Product proxyInstance;

    @Override
    public void create(String id, String name, double price) {
        proxyInstance.create(id, name, price);
    }

    public DynamicProxyProduct(Product p){
        this.product = p;
        this.proxyInstance = (Product) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{Product.class},
                new ProductInvocationHandler(product)
        );
    }

}
