package com.aurum.aurumapp.stock.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.aurum.aurumapp.broker.model.Broker;
import com.aurum.aurumapp.stockdb.model.StockDB;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private StockDB stock;

    @ManyToOne
    private Broker broker;

    private double quantity;

    private double initialValue;

    private String initialDate;

    private double price;

}
