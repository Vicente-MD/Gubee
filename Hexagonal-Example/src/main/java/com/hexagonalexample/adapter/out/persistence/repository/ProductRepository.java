package com.hexagonalexample.adapter.out.persistence.repository;

import com.hexagonalexample.adapter.out.persistence.dto.ProductRepositoryDTO;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<ProductRepositoryDTO> getById(int id);

    List<ProductRepositoryDTO> getAll();

    void save(ProductRepositoryDTO product);

    void update(ProductRepositoryDTO product);

    void delete(int id);
}