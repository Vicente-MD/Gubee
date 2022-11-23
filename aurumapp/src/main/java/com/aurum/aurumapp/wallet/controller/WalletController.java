package com.aurum.aurumapp.wallet.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurum.aurumapp.wallet.model.Wallet;
import com.aurum.aurumapp.wallet.service.WalletService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/wallet")
@AllArgsConstructor
public class WalletController {
    
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Long> createWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.created(URI.create("/api/wallet/" + walletService.createWallet(wallet).getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable("id") long id) {
        return ResponseEntity.ok(walletService.getWalletById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable("id") long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getWallets() {
        return ResponseEntity.ok(walletService.getWallets());
    }
    
}
