package com.aurum.aurumapp.treasurydirect.controller;

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

import com.aurum.aurumapp.treasurydirect.model.TreasuryDirect;
import com.aurum.aurumapp.treasurydirect.model.TreasuryDirectDTO;
import com.aurum.aurumapp.treasurydirect.service.TreasuryDirectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/treasuryDirect")
@AllArgsConstructor
public class TreasuryDirectController {

    private final TreasuryDirectService treasuryDirectService;
    
    @PostMapping
    public ResponseEntity<Long> createTreasuryDirect(@RequestBody TreasuryDirectDTO treasuryDirect) {
        return ResponseEntity.created(URI.create("/api/treasuryDirect/" + treasuryDirectService.createTreasuryDirect(treasuryDirect).getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreasuryDirect> getTreasuryDirectById(@PathVariable("id") long id) {
        return ResponseEntity.ok(treasuryDirectService.getTreasuryDirectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreasuryDirect(@PathVariable("id") long id) {
        treasuryDirectService.deleteTreasuryDirect(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TreasuryDirect>> getTreasuryDirects() {
        return ResponseEntity.ok(treasuryDirectService.getTreasuryDirects());
    }

}
