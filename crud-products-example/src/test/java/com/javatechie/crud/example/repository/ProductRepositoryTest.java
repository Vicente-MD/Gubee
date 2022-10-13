package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;

    @Test
    void shouldGetProductsBelowPrice(){
        // given
        List<Product> products = Arrays.asList(
                new Product(5, "iPhone", 10, 3500),
                new Product(6, "Samsung", 8, 3200),
                new Product(7, "Xiaomi", 9, 3211),
                new Product(8, "Motorola", 6, 2988));
        repository.saveAll(products);

        // when
        List<Product> productsBelowThePrice = repository.getProductsBelowPrice(3210);

        // then
        assertThat(productsBelowThePrice.size()).isEqualTo(2);
    }

    @Test
    void shouldGetProductsAbovePrice(){
        // given
        List<Product> products = Arrays.asList(
                new Product(5, "iPhone", 10, 3500),
                new Product(6, "Samsung", 8, 3200),
                new Product(7, "Xiaomi", 9, 3211),
                new Product(8, "Motorola", 6, 2988));
        repository.saveAll(products);

        // when
        List<Product> productsAboveThePrice = repository.getProductsAbovePrice(3210);

        // then
        assertThat(productsAboveThePrice.size()).isEqualTo(2);
    }

    @Test
    void shouldGetProductsBelowQuantity(){
        // given
        List<Product> products = Arrays.asList(
                new Product(5, "iPhone", 10, 3500),
                new Product(6, "Samsung", 8, 3200),
                new Product(7, "Xiaomi", 9, 3211),
                new Product(8, "Motorola", 6, 2988));
        repository.saveAll(products);

        // when
        List<Product> productsBelowTheQuantity = repository.getProductsBelowQuantity(8);

        // then
        assertThat(productsBelowTheQuantity.size()).isEqualTo(2);
    }

    @Test
    void shouldGetProductsAboveQuantity(){
        // given
        List<Product> products = Arrays.asList(
                new Product(5, "iPhone", 10, 3500),
                new Product(6, "Samsung", 8, 3200),
                new Product(7, "Xiaomi", 9, 3211),
                new Product(8, "Motorola", 6, 2988));
        repository.saveAll(products);

        // when
        List<Product> productsAboveTheQuantity = repository.getProductsAboveQuantity(9);

        // then
        assertThat(productsAboveTheQuantity.size()).isEqualTo(2);
    }

}