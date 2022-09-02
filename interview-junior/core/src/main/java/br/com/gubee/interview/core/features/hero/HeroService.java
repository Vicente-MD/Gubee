package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsRepository;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

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
        Hero result = heroRepository.findHeroById(id);
        return result;
    }

    @Transactional
    public List<Hero> findAllHeroes() {
        List<Hero> result = heroRepository.findAllHeroes();
        return result;
    }

    @Transactional
    public Hero findHeroByName(String name) {
        Hero result = heroRepository.findHeroByName(name);
        return result;
    }

    @Transactional
    public void deleteHeroById(UUID id) {
        heroRepository.findHeroById(id);
        heroRepository.deleteHeroById(id);

    }

    @Transactional
    public void updateHero(CreateHeroRequest hero, UUID uuid) {
        heroRepository.findHeroById(uuid);
        heroRepository.updateHero(hero, uuid);
    }

    public JSONObject compareHeroes(UUID id1, UUID id2) {

        Hero hero1 = heroRepository.findHeroById(id1);
        Hero hero2 = heroRepository.findHeroById(id2);

        JSONObject result = new JSONObject();

        result.put("hero_1", hero1.getId());
        result.put("hero_2", hero2.getId());

        PowerStats statsHero1 = powerStatsRepository.findById(hero1.getPowerStatsId());
        PowerStats statsHero2 = powerStatsRepository.findById(hero2.getPowerStatsId());

        Integer strength = statsHero1.getStrength() - statsHero2.getStrength();
        Integer agility = statsHero1.getAgility() - statsHero2.getAgility();
        Integer dexterity = statsHero1.getDexterity() - statsHero2.getDexterity();
        Integer intelligence = statsHero1.getIntelligence() - statsHero2.getIntelligence();

        result.put("strength", strength);
        result.put("agility", agility);
        result.put("dexterity", dexterity);
        result.put("intelligence", intelligence);

        return result;
    }

}
