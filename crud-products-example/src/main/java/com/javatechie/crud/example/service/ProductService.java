package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public void deleteProduct(int id) {
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
    }

    public Product updateProduct(Product product) {
        if(repository.findById(product.getId()).isPresent()){
            Product existingProduct = repository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        }
        return null;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public List<Product> getProductsBelowPrice(double priceGiven){
       return repository.getProductsBelowPrice(priceGiven);
    }

    public List<Product> getProductsAbovePrice(double priceGiven){
        return repository.getProductsAbovePrice(priceGiven);
    }

    public List<Product> getProductsBelowQuantity(double quantityGiven){
        return repository.getProductsBelowQuantity(quantityGiven);
    }

    public List<Product> getProductsAboveQuantity(double quantityGiven){
        return repository.getProductsAboveQuantity(quantityGiven);
    }
}
