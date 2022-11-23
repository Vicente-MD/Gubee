package com.aurum.aurumapp.stockdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurum.aurumapp.stockdb.model.StockDB;

@Repository
public interface StockDBRepository extends JpaRepository<StockDB, String> {
}
