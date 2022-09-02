package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    private final HeroService heroService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Validated @RequestBody CreateHeroRequest createHeroRequest) {
        final UUID id = heroService.create(createHeroRequest);
        return created(URI.create(format("/api/v1/heroes/%s", id))).build();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getHeroById(@PathVariable("id") UUID id) {
        Hero hero = heroService.findHeroById(id);
        return ResponseEntity.ok(hero);
    }

    @GetMapping("getAll")
    public List<Hero> getAllHeroes() {
        List<Hero> hero = heroService.findAllHeroes();
        return hero;
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> findHeroByName(@PathVariable("name") String name) {
        Hero hero = heroService.findHeroByName(name);
        return ResponseEntity.ok(hero);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        heroService.deleteHeroById(id);
        return ResponseEntity.ok().body("Hero deleted!!!");
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateHero(@RequestBody CreateHeroRequest createHeroRequest, @PathVariable("id") UUID id) {
        heroService.updateHero(createHeroRequest, id);
        return ResponseEntity.ok("");
    }

    @GetMapping("compare/{id1}/{id2}")
    public ResponseEntity<?> compareHeroes(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        JSONObject heroesComparison = heroService.compareHeroes(id1, id2);
        return ResponseEntity.ok(heroesComparison.toString());
    }
}

