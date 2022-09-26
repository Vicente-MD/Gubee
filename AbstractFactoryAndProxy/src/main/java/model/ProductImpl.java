package model;

import annotation.Transaction;

public class ProductImpl implements Product {
    private String id;
    private String name;
    private double price;

    @Override
    @Transaction
    public void create(String id, String name, double price) {
        System.out.println("Criando produto:\n\tId: " + id + "\n\tNome: " + name + "\n\tPrice: " + price);
    }

    public ProductImpl() { }

    public ProductImpl(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
