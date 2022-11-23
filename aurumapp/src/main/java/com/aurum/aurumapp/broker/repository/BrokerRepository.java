package com.aurum.aurumapp.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurum.aurumapp.broker.model.Broker;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
