package proxy;

import annotation.Transaction;
import model.Product;
import model.ProductImpl;

import java.lang.reflect.Method;

public class ProxyProduct implements Product {
    private Product product;
    private String id;

    @Override
    public void create(String id, String name, double price) {
        try {
            Method method = product.getClass().getMethod("create", String.class, String.class, double.class);
            if (method.isAnnotationPresent(Transaction.class)) {
                System.out.println("Iniciando execução do método " + method);
                product.create(id, name, price);
                System.out.println("Finalizando execução do método " + method + " com sucesso.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Finalizando execução com erro.");
        }
    }

    public ProxyProduct() {
        this.product = new ProductImpl();
    }
}
