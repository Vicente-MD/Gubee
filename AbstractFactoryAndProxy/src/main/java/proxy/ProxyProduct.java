package proxy;

import annotation.Transaction;
import factory.FactoryProduct;
import factory.FactoryProductEnum;
import model.Product;

import java.lang.reflect.Method;

public class ProxyProduct implements Product {
    private Product product = FactoryProduct.getEnum(FactoryProductEnum.DEFAULT).instantiateProduct();

    @Override
    public void create() {
        try {
            Method method = product.getClass().getMethod("create");
            if (method.isAnnotationPresent(Transaction.class)) {
                System.out.println("Iniciando execução do método " + method);
                product.create();
                System.out.println("Finalizando execução do método " + method + " com sucesso.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Finalizando execução com erro.");
        }
    }
}
