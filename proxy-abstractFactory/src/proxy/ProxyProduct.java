package proxy;

import annotation.Transaction;
import factory.ServiceFactory;
import model.Product;
import service.ProductService;
import service.Service;

import java.lang.reflect.Method;

public class ProxyProduct implements Service {
    private Service productService = ServiceFactory.getService();

    public void create(Product p) {
        try {
            Method createMethod = ProductService.class.getDeclaredMethod("create", Product.class);
            if(createMethod.isAnnotationPresent(Transaction.class)){
                System.out.println("Iniciando execução do método " + createMethod.getName()+".");
                productService.create(p);
                System.out.println("Finalizando execução do método " + createMethod.getName()+" com sucesso.");
            } else{
                System.out.println("Finalizando execução com erro. ");
            }
        }catch (NoSuchMethodException  e){
            System.out.println("Finalizando execução com erro. ");
        }
    }

    public ProxyProduct(){}
}
