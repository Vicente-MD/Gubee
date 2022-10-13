package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.HeroComparison;
import br.com.gubee.interview.model.dto.HeroDTO;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "id/{id}")
    public ResponseEntity<HeroDTO> getHeroById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(heroService.findHeroById(id));
    }

    @GetMapping("getAll")
    public List<HeroDTO> getAllHeroes() {
        return heroService.findAllHeroes();
    }

    @GetMapping("name/{name}")
    public ResponseEntity<HeroDTO> findHeroByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(heroService.findHeroByName(name));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        heroService.deleteHeroById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateHero(@RequestBody CreateHeroRequest createHeroRequest, @PathVariable("id") UUID id) {
        heroService.updateHero(createHeroRequest, id);
        return ResponseEntity.ok("");
    }

    @GetMapping("compare/{id1}/{id2}")
    public ResponseEntity<HeroComparison> compareHeroes(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        HeroComparison heroesComparison = heroService.compareHeroes(id1, id2);
        return ResponseEntity.ok(heroesComparison);
    }
}