package factory;

import model.Product;

public enum FactoryProductEnum {
    PROXY_REFLECTION(new FactoryProxyProduct()),
    PROXY_DYNAMIC(new FactoryDynamicProxyProduct()),
    DEFAULT(new FactoryProductDefault());


    private final FactoryProduct factoryProduct;

    public FactoryProduct getFactory() {
        return factoryProduct;
    }

    public static Product getFactoryProduct(){
        return FactoryProductEnum.PROXY_DYNAMIC.getFactory().newProduct();
    }

    FactoryProductEnum(FactoryProduct factoryProduct) {
        this.factoryProduct = factoryProduct;
    }
}
