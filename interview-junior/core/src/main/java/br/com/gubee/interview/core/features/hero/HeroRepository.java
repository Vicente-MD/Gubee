package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public UUID create(Hero hero) {
        final Map<String, Object> params = Map.of("name", hero.getName(),
                "race", hero.getRace().name(),
                "powerStatsId", hero.getPowerStatsId());
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_HERO_QUERY,
                params,
                UUID.class);
    }

    public Optional<HeroDTO> findHeroById(UUID uuid) {
        try {
            SqlParameterSource params = new MapSqlParameterSource("id", uuid);
            HeroDTO hero = namedParameterJdbcTemplate.queryForObject(
                    FIND_HERO_BY_ID_QUERY,
                    params,
                    (rs, rows) -> HeroDTO.builder()
                            .id(UUID.fromString(rs.getString("id")))
                            .name(rs.getString("name"))
                            .race(Race.valueOf(rs.getString("race")))
                            .powerStats(PowerStats.builder().id(UUID.fromString(rs.getString("power_stats_id"))).build())
                            .build());
            return Optional.of(hero);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<HeroDTO> findAllHeroes() {
        SqlParameterSource params = new MapSqlParameterSource();
        List<HeroDTO> result = namedParameterJdbcTemplate.query(
                FIND_ALL_HEROES,
                params,
                (rs, rows) -> HeroDTO.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .name(rs.getString("name"))
                        .race(Race.valueOf(rs.getString("race")))
                        .powerStats(PowerStats.builder().id(UUID.fromString(rs.getString("power_stats_id"))).build())
                        .build());
        return result;
    }

    public Optional<HeroDTO> findHeroByName(String name) {
        try {
            SqlParameterSource params = new MapSqlParameterSource("name", name);
            HeroDTO hero = namedParameterJdbcTemplate.queryForObject(
                    FIND_HERO_BY_NAME,
                    params,
                    (rs, rows) -> HeroDTO.builder()
                            .id(UUID.fromString(rs.getString("id")))
                            .name(rs.getString("name"))
                            .race(Race.valueOf(rs.getString("race")))
                            .powerStats(PowerStats.builder().id(UUID.fromString(rs.getString("power_stats_id"))).build())
                            .build());
            return Optional.of(hero);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteHeroById(UUID uuid) {
        SqlParameterSource params = new MapSqlParameterSource("id", uuid);
        namedParameterJdbcTemplate.update(DELETE_HERO, params);
    }

    public void updateHero(CreateHeroRequest hero, UUID uuid) {
        SqlParameterSource params = new MapSqlParameterSource("name", hero.getName())
                .addValue("race", hero.getRace().name())
                .addValue("id", uuid);
        namedParameterJdbcTemplate.update(UPDATE_HERO, params);
    }
}
