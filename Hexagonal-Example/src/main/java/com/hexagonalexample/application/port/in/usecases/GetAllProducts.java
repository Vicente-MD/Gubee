package com.hexagonalexample.application.port.in.usecases;

import com.hexagonalexample.domain.Product;

import java.util.List;

public interface GetAllProducts {
    List<Product> getAllProducts();
}
