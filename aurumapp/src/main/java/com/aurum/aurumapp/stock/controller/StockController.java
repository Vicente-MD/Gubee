package com.aurum.aurumapp.stock.controller;

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

import com.aurum.aurumapp.stock.model.Stock;
import com.aurum.aurumapp.stock.model.StockDTO;
import com.aurum.aurumapp.stock.service.StockService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/stock")
@RestController
@AllArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Long> createStock(@RequestBody StockDTO stock) {
        return ResponseEntity.created(URI.create("/api/stock/" + stockService.createStock(stock).getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable("id") long id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable("id") long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getStocks() {
        return ResponseEntity.ok(stockService.getStocks());
    }
    
}
