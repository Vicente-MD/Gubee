package factory;

public enum FactoryProductEnum {

    PROXY_REFLECTION(new FactoryProxyProduct()),
    PROXY_DYNAMIC(new FactoryDynamicProxyProduct()),
    DEFAULT(new FactoryProductDefault());

    public static FactoryProduct getFactoryProduct() {
        return FactoryProductEnum.PROXY_DYNAMIC.getFactory();
    }

    private final FactoryProduct factoryProduct;

    public FactoryProduct getFactory() {
        return factoryProduct;
    }

    FactoryProductEnum(FactoryProduct factoryProduct) {
        this.factoryProduct = factoryProduct;
    }
}
