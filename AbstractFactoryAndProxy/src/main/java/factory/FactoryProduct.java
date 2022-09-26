package factory;

import model.Product;

public interface FactoryProduct {
    static FactoryProduct getEnum(FactoryProductEnum factoryProductEnum) {
        return factoryProductEnum.getFactory();
    }

    Product instantiateProduct();
}
