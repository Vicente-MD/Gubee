package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);

    @Query(value = "SELECT * FROM product WHERE price <= ?1", nativeQuery = true)
    List<Product> getProductsBelowPrice(double priceGiven);

    @Query(value = "SELECT * FROM product WHERE price >= ?1", nativeQuery = true)
    List<Product> getProductsAbovePrice(double priceGiven);

    @Query(value = "SELECT * FROM product WHERE quantity <= ?1", nativeQuery = true)
    List<Product> getProductsBelowQuantity(double quantityGiven);

    @Query(value = "SELECT * FROM product WHERE quantity >= ?1", nativeQuery = true)
    List<Product> getProductsAboveQuantity(double quantityGiven);
}

