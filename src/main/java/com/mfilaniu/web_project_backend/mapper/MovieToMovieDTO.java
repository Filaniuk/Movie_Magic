package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.entity.movies.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovieToMovieDTO implements Mapper<Movie, MovieDTO> {

    @Override
    public MovieDTO mapFrom(Movie movie) {
        return MovieDTO.builder()
                .movieId(movie.getId())
                .title(movie.getTitle())
                .releaseDate(movie.getReleaseDate().toString())
                .adult(movie.getAdult())
                .linkToImage(movie.getPosterLink())
                .voteAverage(movie.getVoteAverage())
                .voteCount(movie.getVoteCount())
                .popularity(movie.getPopularity())
                .overview(movie.getOverview())
                .build();
    }
}
