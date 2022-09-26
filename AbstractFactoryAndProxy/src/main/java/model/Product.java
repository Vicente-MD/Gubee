package model;

import annotation.Transaction;

public interface Product {
    @Transaction
    public void create(String id, String name, double price);
}
