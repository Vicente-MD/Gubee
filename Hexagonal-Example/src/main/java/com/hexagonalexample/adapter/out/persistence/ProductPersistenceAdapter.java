package com.hexagonalexample.adapter.out.persistence;

import com.hexagonalexample.adapter.out.persistence.dto.ProductRepositoryDTO;
import com.hexagonalexample.adapter.out.persistence.repository.ProductRepository;
import com.hexagonalexample.application.port.in.usecases.*;
import com.hexagonalexample.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductPersistenceAdapter implements CreateProduct, GetAllProducts, DeleteProduct, UpdateProduct, FindByIdProduct {

    private final ProductRepository repository;

    public ProductPersistenceAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createProduct(ProductDto product) {
        repository.save(new ProductRepositoryDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()));
    }

    @Override
    public void deleteProduct(int id) {
        repository.delete(id);
    }

    @Override
    public Product findById(int id) {
        var p = repository.getById(id).get();
        return new Product(p.getId(), p.getName(), p.getQuantity(), p.getPrice());
    }

    @Override
    public void updateProduct(ProductDto product) {
        repository.update(new ProductRepositoryDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (ProductRepositoryDTO p : repository.getAll()) {
            allProducts.add(new Product(p.getId(), p.getName(), p.getQuantity(), p.getPrice()));
        }
        return allProducts;
    }
}

