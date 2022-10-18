package com.hexagonalexample.adapter.in.web.controllers;

import com.hexagonalexample.application.port.in.usecases.GetAllProducts;
import com.hexagonalexample.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class GetAllProductsController {

    private final GetAllProducts getProducts;

    public GetAllProductsController(GetAllProducts getProducts) {
        this.getProducts = getProducts;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return ResponseEntity.ok(getProducts.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}