package com.aurum.aurumapp.stockdb.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aurum.aurumapp.stockdb.model.StockDB;
import com.aurum.aurumapp.stockdb.repository.StockDBRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockDBService {
    private final StockDBRepository stockDBRepository;

    @Transactional
    public StockDB createStockDB(StockDB stockDB) {
        return stockDBRepository.save(stockDB);
    }

    @Transactional
    public StockDB getStockDBById(String id) {
        var stockDB = stockDBRepository.findById(id);
        if (stockDB.isPresent())
            return stockDB.get();
        return null;
    }

    @Transactional
    public void deleteStockDB(String id) {
        stockDBRepository.deleteById(id);
    }

    @Transactional
    public List<StockDB> getStockDBs() {
        return stockDBRepository.findAll();
    }
}
