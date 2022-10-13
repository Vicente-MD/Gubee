package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @AfterEach
    void tearDown() {
        service.deleteAll();
    }

    @Test
    void getProductsBelowPrice() {
        // given
        List<Product> products = Arrays.asList(
                new Product(10, "iPhone", 10, 3500),
                new Product(11, "Samsung", 8, 3200),
                new Product(12, "Xiaomi", 9, 3211),
                new Product(13, "Motorola", 6, 2988));
        var list = service.saveProducts(products);
        double priceGiven = 3200.00;

        // when
        var result = service.getProductsBelowPrice(priceGiven);

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void getProductsAbovePrice() {
        // given
        List<Product> products = Arrays.asList(
                new Product(10, "iPhone", 10, 3500),
                new Product(11, "Samsung", 8, 3200),
                new Product(12, "Xiaomi", 9, 3211),
                new Product(13, "Motorola", 6, 2988));
        var list = service.saveProducts(products);
        double priceGiven = 3210.00;
        // when
        var result = service.getProductsAbovePrice(priceGiven);
        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void getProductsBelowQuantity() {
        // given
        List<Product> products = Arrays.asList(
                new Product(10, "iPhone", 10, 3500),
                new Product(11, "Samsung", 8, 3200),
                new Product(12, "Xiaomi", 9, 3211),
                new Product(13, "Motorola", 6, 2988));
        var list = service.saveProducts(products);
        double quantityGiven = 8;
        // when
        var result  = service.getProductsBelowQuantity(quantityGiven);
        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void getProductsAboveQuantity() {
        // given
        List<Product> products = Arrays.asList(
                new Product(10, "iPhone", 10, 3500),
                new Product(11, "Samsung", 8, 3200),
                new Product(12, "Xiaomi", 9, 3211),
                new Product(13, "Motorola", 6, 2988));
        var list = service.saveProducts(products);
        double quantityGiven = 9;
        // when
        var result  = service.getProductsAboveQuantity(quantityGiven);
        // then
        assertThat(result.size()).isEqualTo(2);
    }
}