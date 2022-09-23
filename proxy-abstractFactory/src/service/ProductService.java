package service;

import annotation.Transaction;
import model.Product;

public class ProductService implements Service{
    @Transaction
    public void create(Product product){
        System.out.println("Salvando Produto:");
        System.out.println("\tNome: "+product.getName()+"\n\tPreço: "+product.getPrice());
    }
}
