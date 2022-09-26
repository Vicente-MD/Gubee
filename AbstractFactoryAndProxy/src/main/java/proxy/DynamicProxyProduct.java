package proxy;

import app.Main;
import model.Product;
import model.ProductImpl;

import java.lang.reflect.Proxy;

public class DynamicProxyProduct implements Product {
    private final Product product;

    @Override
    public void create(String id, String name, double price) {
        getProxyInstance().create(id, name, price);
    }

    public DynamicProxyProduct() {
        this.product = new ProductImpl();
    }

    private Product getProxyInstance() {
        return (Product) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{Product.class},
                new ProductInvocationHandler(product)
        );
    }
}
