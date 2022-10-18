package com.hexagonalexample.application.port.in.usecases;

import com.hexagonalexample.domain.Product;

public interface FindByIdProduct {
    Product findById(int id);
}
