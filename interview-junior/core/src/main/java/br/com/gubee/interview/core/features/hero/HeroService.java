package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.exception.NotFoundException;
import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroComparison;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
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
    public HeroDTO findHeroById(UUID id) {
        HeroDTO hero = heroRepository.findHeroById(id).orElseThrow(() -> new NotFoundException("Hero not found."));
        hero.setPowerStats(powerStatsRepository.findById(hero.getPowerStats().getId()).orElseThrow(()-> new NotFoundException("Hero not found.")));
        return hero;
    }

    @Transactional
    public List<HeroDTO> findAllHeroes() {
        List<HeroDTO> heroes = heroRepository.findAllHeroes();
        for(HeroDTO hero : heroes){
            hero.setPowerStats(powerStatsRepository.findById(hero.getPowerStats().getId()).orElseThrow(()-> new NotFoundException("Hero not found.")));
        }
        return heroes;
    }

    @Transactional
    public HeroDTO findHeroByName(String name) {
        HeroDTO hero = heroRepository.findHeroByName(name).orElse(null);
        if(hero!=null)
            hero.setPowerStats(powerStatsRepository.findById(hero.getPowerStats().getId()).orElseThrow(() -> new NotFoundException("Hero not found.")));
        return hero;
    }

    @Transactional
    public void deleteHeroById(UUID id) {
        heroRepository.findHeroById(id).orElseThrow(() -> new NotFoundException("Hero not found."));
        heroRepository.deleteHeroById(id);
    }

    @Transactional
    public void updateHero(CreateHeroRequest heroRequest, UUID uuid) {
        HeroDTO hero = heroRepository.findHeroById(uuid).orElseThrow(() -> new NotFoundException("Hero not found."));
        heroRepository.updateHero(heroRequest, uuid);
        powerStatsRepository.updateStats(
                hero.getPowerStats().getId(),
                new PowerStats(
                        heroRequest.getStrength(),
                        heroRequest.getAgility(),
                        heroRequest.getDexterity(),
                        heroRequest.getIntelligence()));
    }

    public HeroComparison compareHeroes(UUID id1, UUID id2) {
        HeroDTO hero1 = heroRepository.findHeroById(id1).orElseThrow(() -> new NotFoundException("Hero not found."));
        HeroDTO hero2 = heroRepository.findHeroById(id2).orElseThrow(() -> new NotFoundException("Hero not found."));

        PowerStats statsHero1 = powerStatsRepository.findById(hero1.getPowerStats().getId()).orElseThrow(() -> new NotFoundException("Power Statistics not found."));
        PowerStats statsHero2 = powerStatsRepository.findById(hero2.getPowerStats().getId()).orElseThrow(() -> new NotFoundException("Power Statistics not found."));

        int strengthDifference = statsHero1.getStrength() - statsHero2.getStrength();
        int agilityDifference = statsHero1.getAgility() - statsHero2.getAgility();
        int dexterityDifference = statsHero1.getDexterity() - statsHero2.getDexterity();
        int intelligenceDifference = statsHero1.getIntelligence() - statsHero2.getIntelligence();

        return new HeroComparison(id1, id2, strengthDifference, agilityDifference, dexterityDifference, intelligenceDifference);
    }
}
