package com.aurum.aurumapp.stockdb.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StockDB {
    @Id
    private String id;
    private String name;
}
