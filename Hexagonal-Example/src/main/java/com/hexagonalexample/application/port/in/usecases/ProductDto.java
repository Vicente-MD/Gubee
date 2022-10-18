package com.hexagonalexample.application.port.in.usecases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private int quantity;
    private double price;
}
