package com.hexagonalexample.adapter.in.web.controllers;

import com.hexagonalexample.application.port.in.usecases.DeleteProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
public class DeleteProductController {

    private final DeleteProduct deleteProduct;

    public DeleteProductController(DeleteProduct deleteProduct) {
        this.deleteProduct = deleteProduct;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        try {
            deleteProduct.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
