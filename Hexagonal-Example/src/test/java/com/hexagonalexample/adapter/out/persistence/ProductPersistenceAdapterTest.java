package com.hexagonalexample.adapter.out.persistence;

import com.hexagonalexample.adapter.out.persistence.dto.ProductRepositoryDTO;
import com.hexagonalexample.adapter.out.persistence.repository.ProductRepositoryStub;
import com.hexagonalexample.application.port.in.usecases.ProductDto;
import com.hexagonalexample.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ProductPersistenceAdapterTest {

    private ProductRepositoryStub productRepository = new ProductRepositoryStub();
    private ProductPersistenceAdapter productPersistenceAdapter;

    private void createProductRequest() {
        productPersistenceAdapter.createProduct(new ProductDto(0, "primeiro teste", 5, 300));
        productPersistenceAdapter.createProduct(new ProductDto(0, "segundo teste", 7, 80));
        productPersistenceAdapter.createProduct(new ProductDto(0, "terceiro teste", 1, 600));
        productPersistenceAdapter.createProduct(new ProductDto(0, "quarto teste", 3, 5100));
    }

    @BeforeEach
    void setUp() {
        productPersistenceAdapter = new ProductPersistenceAdapter(productRepository);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void deleteProduct() {
        // given
        createProductRequest();
        int id = 1;

        // when
        productPersistenceAdapter.deleteProduct(id);
        Optional<ProductRepositoryDTO> response = productRepository.getById(id);

        // then
        assertThat(response.isEmpty());
    }

    @Test
    void findById() {
        // given
        createProductRequest();
        int id = 1;

        // when
        var response = productPersistenceAdapter.findById(id);

        // then
        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Product.class);
    }

    @Test
    void updateProduct() {
        // given
        createProductRequest();
        ProductDto newProduct = new ProductDto(1, "produto atualizado", 5, 120.99);

        // when
        productPersistenceAdapter.updateProduct(newProduct);
        var response = productRepository.getById(newProduct.getId()).get();

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(newProduct.getId());
        assertThat(response.getName()).isEqualTo(newProduct.getName());
    }

    @Test
    void getAllProducts() {
        // given
        createProductRequest();

        // when
        var response = productPersistenceAdapter.getAllProducts();

        // then
        assertThat(response).isNotNull();
        assertThat(response.get(0).getId()).isEqualTo(1);
        assertThat(response.get(0)).isInstanceOf(Product.class);
    }
}