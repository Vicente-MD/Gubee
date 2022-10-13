package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
public class HeroServiceIT {

    @Autowired
    private HeroService heroService;

    @Autowired
    private HeroController heroController;

    @Test
    public void createHeroWithAllRequiredArguments() {
        heroService.create(createHeroRequest());
    }

    private CreateHeroRequest createHeroRequest() {
        return CreateHeroRequest.builder()
            .name("Batman")
            .agility(5)
            .dexterity(8)
            .strength(6)
            .intelligence(10)
            .race(Race.HUMAN)
            .build();
    }

    @Test
    void findUserByIdShouldReturnUser() {
        //given
        var id = heroService.create(createHeroRequest());
        //when
        var response = heroService.findHeroById(id);
        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(id);
       // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
