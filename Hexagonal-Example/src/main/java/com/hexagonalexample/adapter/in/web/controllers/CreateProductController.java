package com.hexagonalexample.adapter.in.web.controllers;

import com.hexagonalexample.application.port.in.usecases.CreateProduct;
import com.hexagonalexample.application.port.in.usecases.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/api/products")
@RestController
public class CreateProductController {

    private final CreateProduct createProduct;

    public CreateProductController(CreateProduct createProduct) {
        this.createProduct = createProduct;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto product){
        try{
            createProduct.createProduct(product);
            return ResponseEntity.created(URI.create("api/products/")).build();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
