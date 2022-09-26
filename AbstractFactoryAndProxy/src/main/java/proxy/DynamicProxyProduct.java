package proxy;

import app.Main;
import factory.FactoryProduct;
import factory.FactoryProductEnum;
import model.Product;

import java.lang.reflect.Proxy;

public class DynamicProxyProduct implements Product {
    private final Product product = FactoryProduct.getEnum(FactoryProductEnum.DEFAULT).instantiateProduct();

    private final Product proxyInstance = (Product) Proxy.newProxyInstance(
            Main.class.getClassLoader(),
            new Class[]{Product.class},
            new ProductInvocationHandler(product)
    );

    @Override
    public void create() {
        proxyInstance.create();
    }

}
