package com.aurum.aurumapp.investmenttype.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurum.aurumapp.investmenttype.model.InvestmentType;

public interface InvestmentTypeRepository extends JpaRepository<InvestmentType, Long> {
    public Optional<InvestmentType> findByInvestmentType(String investmentType);
}
