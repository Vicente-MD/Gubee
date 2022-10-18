package com.hexagonalexample.adapter.in.web.controllers;

import com.hexagonalexample.application.port.in.usecases.ProductDto;
import com.hexagonalexample.application.port.in.usecases.UpdateProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
public class UpdateProductController {
    private final UpdateProduct updateProduct;

    public UpdateProductController(UpdateProduct updateProduct) {
        this.updateProduct = updateProduct;
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductDto product) {
        try {
            updateProduct.updateProduct(product);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}