package com.janime.dto.anime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnimeDto {
    private String animeName;
    private String animeDescription;
    private LocalDate animeRelease;
    private String animeURL;
}
