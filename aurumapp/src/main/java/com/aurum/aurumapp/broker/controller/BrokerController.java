package com.aurum.aurumapp.broker.controller;

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

import com.aurum.aurumapp.broker.model.Broker;
import com.aurum.aurumapp.broker.service.BrokerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/broker")
@RequiredArgsConstructor
public class BrokerController {

    private final BrokerService brokerService;

    @PostMapping
    public ResponseEntity<Long> createBroker(@RequestBody Broker broker) {
        return ResponseEntity.created(URI.create("/api/broker/" + brokerService.createBroker(broker).getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Broker> getBrokerById(@PathVariable("id") long id) {
        return ResponseEntity.ok(brokerService.getBrokerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBroker(@PathVariable("id") long id) {
        brokerService.deleteBroker(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Broker>> getBrokers() {
        return ResponseEntity.ok(brokerService.getBrokers());
    }

}
