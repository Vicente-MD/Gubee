package br.com.gubee.interview.core.features.hero;


import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeroService heroService;

    @BeforeEach
    public void initTest() {
        when(heroService.create(any())).thenReturn(UUID.randomUUID());
    }

    private CreateHeroRequest createHeroRequest(String name) {
        return CreateHeroRequest.builder()
                .name(name)
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }

    @Test
    void createAHeroWithAllRequiredArguments() throws Exception {
        //given
        // Convert the hero request into a string JSON format stub.
        final String body = objectMapper.writeValueAsString(createHeroRequest("Batman"));

        //when
        final ResultActions resultActions = mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        //then
        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        verify(heroService, times(1)).create(any());
    }

    @Test
    void findHeroByIdWhenIdExists() throws Exception {
        // given
        final UUID heroId = heroService.create(createHeroRequest("Batman"));

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/id/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void findHeroByIdWhenIdDoesNotExist() throws Exception {
        // given
        final String notHeroId = heroService.create(createHeroRequest("Batman")) + "0";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/id/" + notHeroId)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void findHeroByNameWhenNameExists() throws Exception {
        // given
        heroService.create(createHeroRequest("Batman"));
        final String heroName = "Batman";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/name/" + heroName)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void findHeroByNameWhenNameDoesNotExists() throws Exception {
        // given
        heroService.create(createHeroRequest("Batman"));
        final String notHeroName = "Spider-Man";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/name/" + notHeroName)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void deleteHeroWhenHeroExists() throws Exception {
        // given
        final UUID heroId = heroService.create(createHeroRequest("Batman"));

        // when
        final ResultActions resultActions = mockMvc.perform(
                delete("/api/v1/heroes/delete/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void deleteHeroWhenHeroDoesNotExist() throws Exception {
        // given
        final String notHeroId = heroService.create(createHeroRequest("Batman")) + "0";

        // when
        final ResultActions resultActions = mockMvc.perform(
                delete("/api/v1/heroes/delete/" + notHeroId)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void updateHeroWhenHeroExists() throws Exception {
        // given
        final UUID heroId = heroService.create(createHeroRequest("Batman"));
        final String body = objectMapper.writeValueAsString(createHeroRequest("Spider-Man"));

        // when
        final ResultActions resultActions = mockMvc.perform(
                put("/api/v1/heroes/update/" + heroId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void updateHeroWhenHeroDoesNotExist() throws Exception {
        // given
        final String notHeroId = heroService.create(createHeroRequest("Batman")) + "0";
        final String body = objectMapper.writeValueAsString(createHeroRequest("Spider-Man"));

        // when
        final ResultActions resultActions = mockMvc.perform(
                put("/api/v1/heroes/update/" + notHeroId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body));

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void compareHeroesWhenBothHeroesExist() throws Exception {
        // given
        final UUID hero1Id = heroService.create(createHeroRequest("Batman"));
        final UUID hero2Id = heroService.create(createHeroRequest("Spider-Man"));

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/compare/" + hero1Id + "/" + hero2Id)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    void compareHeroesWhenOneHeroDoesNotExist() throws Exception {
        // given
        final UUID hero1Id = heroService.create(createHeroRequest("Batman"));
        final String hero2Id = heroService.create(createHeroRequest("Spider-Man")) + "0";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/compare/" + hero1Id + "/" + hero2Id)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void compareHeroesWhenBothHeroesDoNotExist() throws Exception {
        // given
        final String hero1Id = heroService.create(createHeroRequest("Batman")) + "0";
        final String hero2Id = heroService.create(createHeroRequest("Spider-Man")) + "0";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/api/v1/heroes/compare/" + hero1Id + "/" + hero2Id)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}