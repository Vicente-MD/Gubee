package com.hexagonalexample.adapter.out.persistence.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
public class ProductRepositoryDTO {
    private int id;
    private String name;
    private int quantity;
    private double price;
}