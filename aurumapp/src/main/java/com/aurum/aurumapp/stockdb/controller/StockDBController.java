package com.aurum.aurumapp.stockdb.controller;

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

import com.aurum.aurumapp.stockdb.model.StockDB;
import com.aurum.aurumapp.stockdb.service.StockDBService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/stockdb")
@RestController
@AllArgsConstructor
public class StockDBController {
    private final StockDBService stockDBService;

    @PostMapping
    public ResponseEntity<Long> createStockDB(@RequestBody StockDB stockDB) {
        return ResponseEntity.created(URI.create("/api/stockDB/" + stockDBService.createStockDB(stockDB).getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDB> getStockDBById(@PathVariable("id") String id) {
        return ResponseEntity.ok(stockDBService.getStockDBById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockDB(@PathVariable("id") String id) {
        stockDBService.deleteStockDB(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<StockDB>> getStockDBs() {
        return ResponseEntity.ok(stockDBService.getStockDBs());
    }
}
