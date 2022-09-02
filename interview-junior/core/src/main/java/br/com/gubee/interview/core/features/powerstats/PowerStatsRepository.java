package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PowerStatsRepository {

    private static final String CREATE_POWER_STATS_QUERY = "INSERT INTO power_stats" +
            " (strength, agility, dexterity, intelligence)" +
            " VALUES (:strength, :agility, :dexterity, :intelligence) RETURNING id";

    private static final String FIND_ALL_STATS = "SELECT * FROM power_stats";

    private static final String FIND_STAT_BY_ID = "SELECT * FROM power_stats WHERE id = :id";

    private static final String UPDATE_STATS = "UPDATE power_stats SET strength = :strength , agility = :agility , dexterity = :dexterity , intelligence = :intelligence  WHERE id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UUID create(PowerStats powerStats) {
        return namedParameterJdbcTemplate.queryForObject(
                CREATE_POWER_STATS_QUERY,
                new BeanPropertySqlParameterSource(powerStats),
                UUID.class);
    }

    public List<PowerStats> findAll() {
        List<PowerStats> result = namedParameterJdbcTemplate.query(
                        FIND_ALL_STATS,
                        (rs, rowNum) -> PowerStats.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .strength(rs.getInt("strength"))
                                .agility(rs.getInt("agility"))
                                .dexterity(rs.getInt("dexterity"))
                                .intelligence(rs.getInt("intelligence"))
                                .build());
        return result;
    }

    public PowerStats findById(UUID uuid) {
        SqlParameterSource params = new MapSqlParameterSource("id", uuid);
        PowerStats result = namedParameterJdbcTemplate.queryForObject(
                FIND_STAT_BY_ID,
                params,
                (rs, rowNum) -> PowerStats.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .strength(rs.getInt("strength"))
                        .agility(rs.getInt("agility"))
                        .dexterity(rs.getInt("dexterity"))
                        .intelligence(rs.getInt("intelligence"))
                        .build());
        return result;
    }

    public void updateStats(PowerStats stats, UUID uuid){
        System.out.println(stats);
        SqlParameterSource params = new MapSqlParameterSource("strength", stats.getStrength())
                .addValue("agility", stats.getAgility())
                .addValue("dexterity", stats.getDexterity())
                .addValue("intelligence", stats.getIntelligence())
                .addValue("id", uuid);
        namedParameterJdbcTemplate.update(UPDATE_STATS, params);
    }
}
