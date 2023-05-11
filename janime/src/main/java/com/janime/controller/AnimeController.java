package com.janime.controller;

import com.janime.domain.Anime;
import com.janime.dto.anime.CreateAnimeDto;
import com.janime.dto.anime.UpdateAnimeDto;
import com.janime.service.AnimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animes")
@Log4j2
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AnimeController {

    private final AnimeService animeService;
    @GetMapping()
    public ResponseEntity<List<Anime>> findAll() {
        return ResponseEntity.status(200).body(animeService.findAllAnime());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findAnimeById(@PathVariable long id) {
        Anime anime = animeService.findAnimeByIdOrThrowNotFoundException(id);
        return ResponseEntity.status(200).body(anime);
    }
    @PostMapping()
    public ResponseEntity<Anime> save(@RequestBody CreateAnimeDto createAnimeDto) {
        return ResponseEntity.status(201).body(animeService.save(createAnimeDto));
    }
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateAnimeDto updateAnimeDto) {
        animeService.replace(updateAnimeDto);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return ResponseEntity.status(200).build();
    }
}
