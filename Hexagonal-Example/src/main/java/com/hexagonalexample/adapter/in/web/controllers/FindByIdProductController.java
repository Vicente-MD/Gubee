package com.hexagonalexample.adapter.in.web.controllers;

import com.hexagonalexample.application.port.in.usecases.FindByIdProduct;
import com.hexagonalexample.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
public class FindByIdProductController {
    private final FindByIdProduct findProduct;

    public FindByIdProductController(FindByIdProduct findProduct) {
        this.findProduct = findProduct;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(findProduct.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

