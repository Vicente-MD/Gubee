package service;

import annotation.Transaction;
import model.Product;

public interface Service {
    @Transaction
    public void create(Product product);
}
