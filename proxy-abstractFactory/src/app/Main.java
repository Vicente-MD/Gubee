package app;

import factory.Factory;
import factory.ProxyFactory;
import model.Product;
import proxy.DynamicProxyProduct;
import proxy.ProxyProduct;
import service.ProductService;
import service.Service;

public class Main {
    public static void main(String[] args) {
        final Factory factory = new ProxyFactory();

        System.out.println("\t****** Iniciando create() com reflection *****");
        ProxyProduct proxyProduct = factory.createProxyProduct();
        proxyProduct.create(new Product("kpod", "iPad", 4500.99));


        System.out.println("\n\n\t****** Iniciando create() com dynamic proxy *****");
        DynamicProxyProduct dynamicProxyProduct = factory.createDynamicProxyProduct();
        dynamicProxyProduct.create(new Product("kpod", "iPad", 4500.99));


    }
}
