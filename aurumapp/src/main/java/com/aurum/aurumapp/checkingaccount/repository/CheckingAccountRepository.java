package com.aurum.aurumapp.checkingaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurum.aurumapp.checkingaccount.model.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
    
}
