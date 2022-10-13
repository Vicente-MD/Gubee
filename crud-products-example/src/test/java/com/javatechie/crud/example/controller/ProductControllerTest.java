package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController controller;

    @AfterEach
    void tearDown() {
        controller.deleteAll();
    }

    @Test
    void shouldAddProduct() {
        // given
        Product p = new Product(7, "iPhone", 10, 3500);

        // when
        ResponseEntity<Product> response = controller.addProduct(p);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("iPhone");
    }

    @Test
    void addProducts() {
        // given
        List<Product> products = Arrays.asList(
                new Product(1, "iPhone", 10, 3500),
                new Product(2, "Samsung", 8, 3200));

        // when
        ResponseEntity<List<Product>> response = controller.addProducts(products);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getName()).isEqualTo("iPhone");
    }

    @Test
    void findProductByIdWhenIdExists() {
        // given
        Product product = new Product(1, "iPhone", 10, 3500);
        int id = controller.addProduct(product).getBody().getId();

        // when
        ResponseEntity<Product> response = controller.findProductById(id);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(id);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void findProductByIdWhenIdDoesNotExist() {
        // given
        Product product = new Product(1, "iPhone", 10, 3500);
        controller.addProduct(product);

        // when
        ResponseEntity<Product> response = controller.findProductById(5987);

        // then
        assertThat(response.getBody()).isNull();
    }

    @Test
    void findProductByName() {
        // given
        Product product = new Product(1, "iPhone", 10, 3500);
        controller.addProduct(product);

        // when
        ResponseEntity<Product> response = controller.findProductByName(product.getName());

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(product.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void findProductByNameWhenNameDoesNotExist() {
        // given
        Product product = new Product(1, "iPhone", 10, 3500);
        controller.addProduct(product);

        // when
        ResponseEntity<Product> response = controller.findProductByName("Fiat Uno");

        // then
        assertThat(response.getBody()).isNull();
    }

    @Test
    void updateProductWhenProductExists() {
        // given
        Product product = new Product(1, "iPhone", 10, 3500);
        int id = controller.addProduct(product).getBody().getId();
        Product productUpdated = new Product(id, "Samsung Galaxy", 10, 3800);

        // when
        ResponseEntity<Product> response = controller.updateProduct(productUpdated);

        // then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(productUpdated.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateProductWhenProductDoesNotExist() {
        // given
        Product product = new Product(1, "iPhone", 10, 3500);
        controller.addProduct(product);
        Product productUpdated = new Product(900, "Samsung Galaxy", 10, 3800);

        // when
        ResponseEntity<Product> response = controller.updateProduct(productUpdated);

        // then
        assertThat(response.getBody()).isNull();
    }

    @Test
    void deleteProduct() {
        // given
        Product product = new Product(5, "iPhone", 10, 3500);
        controller.addProduct(product);

        // when
        ResponseEntity<String> response = controller.deleteProduct(product.getId());

        // then
        assertThat(response.getBody()).isNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}