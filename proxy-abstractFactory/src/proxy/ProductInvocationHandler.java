package proxy;

import annotation.Transaction;
import model.Product;
import service.ProductService;
import service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProductInvocationHandler implements InvocationHandler {
    private final Service target;

    public ProductInvocationHandler(Service target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Method createMethod = target.getClass().getMethod("create", Product.class);
            if (createMethod.isAnnotationPresent(Transaction.class)) {
                System.out.println("Iniciando execução do método " + createMethod.getName() + ".");
                method.invoke(target, args);
                System.out.println("Finalizando execução do método " + createMethod.getName() + " com sucesso.");
            } else {
                System.out.println("Finalizando execução com erro. ");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
