package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.NotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroComparison;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final PowerStatsRepository powerStatsRepository;
    private final HeroRepository heroRepository;

    @Transactional
    public UUID create(CreateHeroRequest createHeroRequest) {
        PowerStats powerStats = new PowerStats(createHeroRequest);
        UUID powerStatsId = powerStatsRepository.create(powerStats);
        return heroRepository.create(new Hero(createHeroRequest, powerStatsId));
    }

    @Transactional
    public Hero findHeroById(UUID id) {
        Hero result = heroRepository.findHeroById(id).orElseThrow(() -> new NotFoundException());
        return result;
    }

    @Transactional
    public List<Hero> findAllHeroes() {
        List<Hero> result = heroRepository.findAllHeroes();
        return result;
    }

    @Transactional
    public Hero findHeroByName(String name) {
        Hero result = heroRepository.findHeroByName(name).orElse(null);
        return result;
    }

    @Transactional
    public void deleteHeroById(UUID id) {
        heroRepository.findHeroById(id).orElseThrow(() -> new NotFoundException());
        heroRepository.deleteHeroById(id);
    }

    @Transactional
    public void updateHero(CreateHeroRequest heroRequest, UUID uuid) {
        Hero hero = heroRepository.findHeroById(uuid).orElseThrow(() -> new NotFoundException());
        heroRepository.updateHero(heroRequest, uuid);
        powerStatsRepository.updateStats(
                hero.getPowerStatsId(),
                new PowerStats(
                        heroRequest.getStrength(),
                        heroRequest.getAgility(),
                        heroRequest.getDexterity(),
                        heroRequest.getIntelligence()));
    }

    public HeroComparison compareHeroes(UUID id1, UUID id2) {
        Hero hero1 = heroRepository.findHeroById(id1).orElseThrow(() -> new NotFoundException());
        Hero hero2 = heroRepository.findHeroById(id2).orElseThrow(() -> new NotFoundException());

        PowerStats statsHero1 = powerStatsRepository.findById(hero1.getPowerStatsId()).orElseThrow(() -> new NotFoundException());
        PowerStats statsHero2 = powerStatsRepository.findById(hero2.getPowerStatsId()).orElseThrow(() -> new NotFoundException());

        int strengthDifference = statsHero1.getStrength() - statsHero2.getStrength();
        int agilityDifference = statsHero1.getAgility() - statsHero2.getAgility();
        int dexterityDifference = statsHero1.getDexterity() - statsHero2.getDexterity();
        int intelligenceDifference = statsHero1.getIntelligence() - statsHero2.getIntelligence();

        return new HeroComparison(id1, id2, strengthDifference, agilityDifference, dexterityDifference, intelligenceDifference);
    }
}
