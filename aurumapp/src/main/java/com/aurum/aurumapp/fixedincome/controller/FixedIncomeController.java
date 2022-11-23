package com.aurum.aurumapp.fixedincome.controller;

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

import com.aurum.aurumapp.fixedincome.model.FixedIncomeDTO;
import com.aurum.aurumapp.fixedincome.model.FixedIncomeModel;
import com.aurum.aurumapp.fixedincome.service.FixedIncomeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fixedIncome")
@AllArgsConstructor
public class FixedIncomeController {

    private final FixedIncomeService fixedIncomeService;
    
    @PostMapping
    public ResponseEntity<Long> createFixedIncome(@RequestBody FixedIncomeDTO fixedIncome) {
        return ResponseEntity.created(URI.create("/api/broker/" + fixedIncomeService.createFixedIncome(fixedIncome).getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FixedIncomeModel> getFixedIncomeById(@PathVariable("id") long id) {
        return ResponseEntity.ok(fixedIncomeService.getFixedIncomeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFixedIncome(@PathVariable("id") long id) {
        fixedIncomeService.deleteFixedIncome(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FixedIncomeModel>> getFixedIncomes() {
        return ResponseEntity.ok(fixedIncomeService.getFixedIncomes());
    }

}
