package com.janime.service;

import com.janime.domain.Anime;
import com.janime.dto.anime.CreateAnimeDto;
import com.janime.dto.anime.UpdateAnimeDto;
import com.janime.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> findAllAnime() {
        List<Anime> animeList = animeRepository.findAll();
        if (animeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum Anime cadastrado!");
        }
        return animeList;
    }

    public Anime findAnimeByIdOrThrowNotFoundException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found"));
    }

    public Anime save(CreateAnimeDto createAnimeDto) {
        Anime anime = Anime.builder()
                .animeName(createAnimeDto.getAnimeName())
                .animeDescription(createAnimeDto.getAnimeDescription())
                .animeRelease(createAnimeDto.getAnimeRelease())
                .animeURL(createAnimeDto.getAnimeURL())
                .build();
        return animeRepository.save(anime);
    }

    public void delete(long id) {
        animeRepository.delete(findAnimeByIdOrThrowNotFoundException(id));
    }

    public void replace(UpdateAnimeDto updateAnimeDto) {
        Anime savedAnime = findAnimeByIdOrThrowNotFoundException(updateAnimeDto.getAnimeId());
        Anime anime = Anime.builder()
                .animeId(savedAnime.getAnimeId())
                .animeName(updateAnimeDto.getAnimeName())
                .animeDescription(updateAnimeDto.getAnimeDescription())
                .animeRelease(updateAnimeDto.getAnimeRelease())
                .animeURL(updateAnimeDto.getAnimeURL())
                .build();
        animeRepository.save(anime);
    }
}
