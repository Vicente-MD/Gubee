package com.aurum.aurumapp.fixedincome.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aurum.aurumapp.fixedincome.model.FixedIncomeDTO;
import com.aurum.aurumapp.fixedincome.model.FixedIncomeModel;
import com.aurum.aurumapp.fixedincome.repository.FixedIncomeRepository;
import com.aurum.aurumapp.investmenttype.repository.InvestmentTypeRepository;
import com.aurum.aurumapp.status.repository.StatusRepository;
import com.aurum.aurumapp.transaction.model.FeignInvestment;
import com.aurum.aurumapp.transaction.model.Transaction;
import com.aurum.aurumapp.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FixedIncomeService {
    private final FixedIncomeRepository fixedIncomeRepository;
    private final TransactionRepository transactionRepository;
    private final InvestmentTypeRepository investmentTypeRepository;
    private final StatusRepository statusRepository;

    @Transactional
    public FixedIncomeModel createFixedIncome(FixedIncomeDTO fixedIncomeDTO) {
        var fixedIncome = fixedIncomeRepository.save(fixedIncomeDTO.getFixedIncome());
        var investment = FeignInvestment.builder()
                .id(fixedIncome.getId())
                .broker(fixedIncome.getBroker())
                .paper(fixedIncome.getPaper())
                .issuer(fixedIncome.getIssuer())
                .initialValue(fixedIncome.getInitialValue())
                .initialDate(fixedIncome.getInitialDate())
                .finalDate(fixedIncome.getFinalDate())
                .yieldRate(fixedIncome.getYieldRate()).build();
        var investmentType = investmentTypeRepository.findByInvestmentType("FIXED_INCOME").get();
        var status = statusRepository.findByStatus("ACTIVE").get();
        var wallet = fixedIncomeDTO.getUser().getWallet();
        var id = "" + investmentType.getId() + investment.getId();
        var transaction = new Transaction(Long.parseLong(id), investment, investmentType, wallet, status);
        transactionRepository.save(transaction);

        return fixedIncome;
    }

    @Transactional
    public FixedIncomeModel getFixedIncomeById(long id) {
        var fixedIncome = fixedIncomeRepository.findById(id);
        if (fixedIncome.isPresent())
            return fixedIncome.get();
        return null;
    }

    @Transactional
    public void deleteFixedIncome(long id) {
        fixedIncomeRepository.deleteById(id);
    }

    @Transactional
    public List<FixedIncomeModel> getFixedIncomes() {
        return fixedIncomeRepository.findAll();
    }
}
