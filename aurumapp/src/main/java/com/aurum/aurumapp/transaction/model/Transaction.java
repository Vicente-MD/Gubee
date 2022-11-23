package com.aurum.aurumapp.transaction.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.aurum.aurumapp.investmenttype.model.InvestmentType;
import com.aurum.aurumapp.status.model.Status;
import com.aurum.aurumapp.wallet.model.Wallet;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private long id;

    @JsonInclude()
    @Transient
    private FeignInvestment investment;

    @ManyToOne
    private InvestmentType investmentType;

    @ManyToOne
    private Wallet wallet;

    @ManyToOne
    private Status status;
}
