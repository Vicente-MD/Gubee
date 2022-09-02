package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeroRepository {

    private static final String CREATE_HERO_QUERY = "INSERT INTO hero" +
            " (name, race, power_stats_id)" +
            " VALUES (:name, :race, :powerStatsId) RETURNING id";
    private static final String FIND_HERO_BY_ID_QUERY = "SELECT * FROM interview_service.hero WHERE id = :id";

    private static final String FIND_ALL_HEROES = "SELECT * FROM interview_service.hero";

    private static final String FIND_HERO_BY_NAME = "SELECT * FROM interview_service.hero WHERE name like :name";

    private static final String DELETE_HERO = "DELETE FROM interview_service.hero WHERE id = :id";

    private static final String UPDATE_HERO = "UPDATE hero SET name = :name , race = :race  WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    final PowerStatsService powerStatsService;

    public UUID create(Hero hero) {
        final Map<String, Object> params = Map.of("name", hero.getName(),
                "race", hero.getRace().name(),
                "powerStatsId", hero.getPowerStatsId());
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class);
    }

    public Hero findHeroById(UUID uuid) {
        SqlParameterSource params = new MapSqlParameterSource("id", uuid);
        Hero hero = namedParameterJdbcTemplate.queryForObject(
                FIND_HERO_BY_ID_QUERY,
                params,
                (rs, rows) -> Hero.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .name(rs.getString("name"))
                        .race(Race.valueOf(rs.getString("race")))
                        .powerStatsId(UUID.fromString(rs.getString("power_stats_id")))
                        .build());
        return hero;
    }

    public List<Hero> findAllHeroes() {
        SqlParameterSource params = new MapSqlParameterSource();
        List<Hero> result = namedParameterJdbcTemplate.query(
                FIND_ALL_HEROES,
                params,
                (rs, rows) -> Hero.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .name(rs.getString("name"))
                        .race(Race.valueOf(rs.getString("race")))
                        .powerStatsId(UUID.fromString(rs.getString("power_stats_id")))
                        .build());
        return result;
    }

    public Hero findHeroByName(String name) {
        SqlParameterSource params = new MapSqlParameterSource("name", name);
        Hero hero = namedParameterJdbcTemplate.queryForObject(
                FIND_HERO_BY_NAME,
                params,
                (rs, rows) -> Hero.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .name(rs.getString("name"))
                        .race(Race.valueOf(rs.getString("race")))
                        .powerStatsId(UUID.fromString(rs.getString("power_stats_id")))
                        .build());
        return hero;

    }

    public void deleteHeroById(UUID uuid){
        SqlParameterSource params = new MapSqlParameterSource("id", uuid);
        namedParameterJdbcTemplate.update(DELETE_HERO, params);
    }

    public void updateHero(CreateHeroRequest hero, UUID uuid){
        SqlParameterSource params = new MapSqlParameterSource("name", hero.getName())
                .addValue("race", hero.getRace().name())
                .addValue("id", uuid);

        powerStatsService.updateStats(
                findHeroById(uuid).getPowerStatsId(),
                new PowerStats(
                        hero.getStrength(),
                        hero.getAgility(),
                        hero.getDexterity(),
                        hero.getIntelligence()));
        namedParameterJdbcTemplate.update(UPDATE_HERO, params);
    }
}
