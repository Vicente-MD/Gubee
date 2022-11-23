package com.aurum.aurumapp.fixedincome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurum.aurumapp.fixedincome.model.FixedIncomeModel;

@Repository
public interface FixedIncomeRepository extends JpaRepository<FixedIncomeModel, Long>{
}
