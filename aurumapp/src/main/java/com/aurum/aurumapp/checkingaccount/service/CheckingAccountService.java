package com.aurum.aurumapp.checkingaccount.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aurum.aurumapp.checkingaccount.model.CheckingAccount;
import com.aurum.aurumapp.checkingaccount.model.CheckingAccountDTO;
import com.aurum.aurumapp.checkingaccount.repository.CheckingAccountRepository;
import com.aurum.aurumapp.investmenttype.repository.InvestmentTypeRepository;
import com.aurum.aurumapp.status.repository.StatusRepository;
import com.aurum.aurumapp.transaction.model.FeignInvestment;
import com.aurum.aurumapp.transaction.model.Transaction;
import com.aurum.aurumapp.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {
    private final CheckingAccountRepository checkingAccountRepository;
    private final TransactionRepository transactionRepository;
    private final InvestmentTypeRepository investmentTypeRepository;
    private final StatusRepository statusRepository;

    @Transactional
    public CheckingAccount createCheckingAccount(CheckingAccountDTO checkingAccountDTO) {
        var checkingAccount = checkingAccountRepository.save(checkingAccountDTO.getCheckingAccount());
        var investment = FeignInvestment.builder()
                .id(checkingAccount.getId())
                .broker(checkingAccount.getBroker())
                .initialValue(checkingAccount.getInitialValue())
                .initialDate(checkingAccount.getInitialDate())
                .yieldRate(checkingAccount.getYieldRate())
                .title(checkingAccount.getTitle()).build();
        var investmentType = investmentTypeRepository.findByInvestmentType("CHECKING_ACCOUNT").get();
        var status = statusRepository.findByStatus("ACTIVE").get();
        var wallet = checkingAccountDTO.getUser().getWallet();
        var id = "" + investmentType.getId() + investment.getId();
        var transaction = new Transaction(Long.parseLong(id), investment, investmentType, wallet, status);
        transactionRepository.save(transaction);

        return checkingAccount;
    }

    @Transactional
    public CheckingAccount getCheckingAccountById(long id) {
        var checkingAccount = checkingAccountRepository.findById(id);
        if (checkingAccount.isPresent())
            return checkingAccount.get();
        return null;
    }

    @Transactional
    public void deleteCheckingAccount(long id) {
        checkingAccountRepository.deleteById(id);
    }

    @Transactional
    public List<CheckingAccount> getCheckingAccounts() {
        return checkingAccountRepository.findAll();
    }
}
