package com.aurum.aurumapp.transaction.model;

import com.aurum.aurumapp.broker.model.Broker;
import com.aurum.aurumapp.stockdb.model.StockDB;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FeignInvestment {
    // todos
    private long id;
    // conta corrente
    private String title;
    // conta corrente, tesouro direto e taxa fixa
    private double yieldRate;
    // todos
    private String initialDate;
    // todos
    private double initialValue;
    // todos
    private Broker broker;
    // taxa fixa
    private String paper;
    // taxa fixa
    private String issuer;
    // taxa fixa e tesouro direto
    private String finalDate;
    // ação
    private StockDB stock;
    // ação
    private double quantity;
    // ação
    private double price;
}
