package com.janime.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "animes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animeId;

    @Column(name = "anime_name")
    private String animeName;

    @Column(name = "anime_description")
    private String animeDescription;

    @Column(name = "anime_release")
    private LocalDate animeRelease;

    @Column(name = "anime_url")
    private String animeURL;

}
