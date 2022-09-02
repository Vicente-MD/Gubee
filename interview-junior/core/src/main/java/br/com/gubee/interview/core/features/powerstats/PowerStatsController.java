package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/powerStats", produces = APPLICATION_JSON_VALUE)
public class PowerStatsController {

    private final PowerStatsService powerStatsService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated @RequestBody PowerStats powerStats) {
        final UUID id = powerStatsService.create(powerStats);
        return created(URI.create(format("/api/v1/powerStats/%s", id))).build();
    }

    @GetMapping("getAll")
    public List<PowerStats> getAllStats() {
        List<PowerStats> stats = powerStatsService.findAllStats();
        return stats;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getStatById(@PathVariable("id") UUID id) {
        PowerStats stats = powerStatsService.findStatById(id);
        return ResponseEntity.ok(stats);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateStats(@PathVariable("id") UUID id, @RequestBody PowerStats stats) {
        powerStatsService.updateStats(id, stats);
        return ResponseEntity.ok("");
    }
}
