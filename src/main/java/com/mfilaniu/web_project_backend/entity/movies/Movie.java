package com.mfilaniu.web_project_backend.entity.movies;

import com.mfilaniu.web_project_backend.entity.user.Citizenship;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(exclude = "users")
@ToString(exclude = "users")
@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "vote_avg")
    private Double voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

    private Double popularity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "poster_link")
    private String posterLink;

    private String overview;

    private Boolean adult;

    @Builder.Default
    @OneToMany(mappedBy = "movie") //TODO: ADD FETCH-TYPE
    private List<UserMovie> users = new ArrayList<>();

}