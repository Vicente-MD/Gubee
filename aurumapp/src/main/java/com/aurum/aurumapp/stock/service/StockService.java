package com.aurum.aurumapp.stock.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aurum.aurumapp.investmenttype.repository.InvestmentTypeRepository;
import com.aurum.aurumapp.status.repository.StatusRepository;
import com.aurum.aurumapp.stock.model.Stock;
import com.aurum.aurumapp.stock.model.StockDTO;
import com.aurum.aurumapp.stock.repository.StockRepository;
import com.aurum.aurumapp.transaction.model.FeignInvestment;
import com.aurum.aurumapp.transaction.model.Transaction;
import com.aurum.aurumapp.transaction.repository.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final TransactionRepository transactionRepository;
    private final InvestmentTypeRepository investmentTypeRepository;
    private final StatusRepository statusRepository;

    @Transactional
    public Stock createStock(StockDTO stockDTO) {
        var stock = stockRepository.save(stockDTO.getStock());
        var investment = FeignInvestment.builder()
                .id(stock.getId())
                .stock(stock.getStock())
                .broker(stock.getBroker())
                .quantity(stock.getQuantity())
                .initialValue(stock.getInitialValue())
                .initialDate(stock.getInitialDate())
                .price(stock.getPrice()).build();
        var investmentType = investmentTypeRepository.findByInvestmentType("STOCK").get();
        var status = statusRepository.findByStatus("ACTIVE").get();
        var wallet = stockDTO.getUser().getWallet();
        var id = "" + investmentType.getId() + stock.getId();
        var transaction = new Transaction(Long.parseLong(id), investment, investmentType, wallet, status);
        transactionRepository.save(transaction);

        return stock;
    }

    @Transactional
    public Stock getStockById(long id) {
        var stock = stockRepository.findById(id);
        if (stock.isPresent())
            return stock.get();
        return null;
    }

    @Transactional
    public void deleteStock(long id) {
        stockRepository.deleteById(id);
    }

    @Transactional
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }
}
