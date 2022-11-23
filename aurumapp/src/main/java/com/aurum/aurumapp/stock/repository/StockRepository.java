package com.aurum.aurumapp.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurum.aurumapp.stock.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    
}
