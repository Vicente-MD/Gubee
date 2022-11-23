package com.aurum.aurumapp.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurum.aurumapp.transaction.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
