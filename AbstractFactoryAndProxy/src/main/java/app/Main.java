package app;

import factory.FactoryProductEnum;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();

        productList.add(FactoryProductEnum.getFactoryProduct().newProduct());
        productList.get(0).create("tttttt", "Moto Z Power", 3500.55);

        productList.add(FactoryProductEnum.getFactoryProduct().newProduct());
        productList.get(1).create("yyyyyy", "Samsung Note 10", 3498.55);

    }
}
