package app;

import factory.FactoryProduct;
import factory.FactoryProductEnum;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();

        productList.add(FactoryProduct.getEnum(FactoryProductEnum.PROXY_REFLECTION).instantiateProduct());
        productList.get(0).create();

        productList.add(FactoryProduct.getEnum(FactoryProductEnum.PROXY_DYNAMIC).instantiateProduct());
        productList.get(1).create();

    }
}
