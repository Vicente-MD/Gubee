package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PowerStatsService {

    private final PowerStatsRepository powerStatsRepository;

    @Transactional
    public UUID create(PowerStats powerStats) {
        return powerStatsRepository.create(powerStats);
    }

    public List<PowerStats> findAllStats() {
        return powerStatsRepository.findAll();
    }

    public PowerStats findStatById(UUID uuid) {
        PowerStats stats = powerStatsRepository.findById(uuid);
        return stats;
    }

    public void updateStats(UUID uuid, PowerStats stats) {
        findStatById(uuid);
        powerStatsRepository.updateStats(stats, uuid);
    }

}
