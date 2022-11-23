package com.aurum.aurumapp.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurum.aurumapp.wallet.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
}
