package factory;

import service.ProductService;

public class ServiceFactory {
    public static ProductService getService(){
        return new ProductService();
    }
}
