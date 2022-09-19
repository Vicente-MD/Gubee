package br.com.gubee.interview.model.dto;

import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.model.enums.Race;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroDTO {
    private UUID id;
    private String name;
    private Race race;
    private PowerStats powerStats;
    private boolean enabled;
}
