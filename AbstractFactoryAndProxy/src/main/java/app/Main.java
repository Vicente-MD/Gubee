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
        productList.get(0).create("tttttt", "Moto Z Power", 3500.55);

        productList.add(FactoryProduct.getEnum(FactoryProductEnum.PROXY_REFLECTION).instantiateProduct());
        productList.get(1).create("yyyyyy", "Samsung Note 10", 3498.55);

        productList.add(FactoryProduct.getEnum(FactoryProductEnum.PROXY_DYNAMIC).instantiateProduct());
        productList.get(2).create("xxxxxx", "iPhone X", 3599.90);

        productList.add(FactoryProduct.getEnum(FactoryProductEnum.PROXY_DYNAMIC).instantiateProduct());
        productList.get(3).create("zzzzzzz", "Xiomi Note 11 Pro", 3000.86);

    }
}
