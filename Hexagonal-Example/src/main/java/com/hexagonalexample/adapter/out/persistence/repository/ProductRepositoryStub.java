package com.hexagonalexample.adapter.out.persistence.repository;

import com.hexagonalexample.adapter.out.persistence.dto.ProductRepositoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryStub implements ProductRepository {

    private final List<ProductRepositoryDTO> productList = new ArrayList<>();
    int id;

    @Override
    public Optional<ProductRepositoryDTO> getById(int id) {
        for (ProductRepositoryDTO p : productList) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ProductRepositoryDTO> getAll() {
        return productList;
    }

    @Override
    public void save(ProductRepositoryDTO product) {
        id += 1;
        product.setId(id);
        productList.add(product);
    }

    @Override
    public void update(ProductRepositoryDTO product) {
        var p = getById(product.getId());
        if (p.isPresent()) {
            int index = productList.indexOf(p.get());
            productList.set(index, product);
        }
    }

    @Override
    public void delete(int id) {
        ProductRepositoryDTO p = getById(id).get();
        productList.remove(p);
    }

    public void deleteAll() {
        productList.clear();
    }
}
