package com.aurum.aurumapp.transaction.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurum.aurumapp.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionRepository transactionRepository;

    @GetMapping
    public ResponseEntity<?> listAllTransactions(){
        return ResponseEntity.ok(transactionRepository.findAll());
    }
}
