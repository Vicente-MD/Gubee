package com.aurum.aurumapp.status.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurum.aurumapp.status.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
    public Optional<Status> findByStatus(String status);
}
