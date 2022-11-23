package com.aurum.aurumapp.wallet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aurum.aurumapp.wallet.model.Wallet;
import com.aurum.aurumapp.wallet.repository.WalletRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    @Transactional
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet getWalletById(long id) {
        var wallet = walletRepository.findById(id);
        if (wallet.isPresent())
            return wallet.get();
        return null;
    }

    @Transactional
    public void deleteWallet(long id) {
        walletRepository.deleteById(id);
    }

    @Transactional
    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }
}
