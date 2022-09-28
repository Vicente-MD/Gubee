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

}
