package model;

import annotation.Transaction;

public interface Product {
    @Transaction
    public void create();
}
