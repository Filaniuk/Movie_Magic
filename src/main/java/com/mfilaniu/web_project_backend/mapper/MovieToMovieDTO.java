package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.entity.movies.Movie;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
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
                .voteAverage(String.format("%.2f", movie.getVoteAverage()).replace(',', '.'))
                .voteCount(movie.getVoteCount())
                .popularity(String.format("%.1f", movie.getPopularity()).replace(',', '.'))
                .overview(movie.getOverview())
                .build();
    }
}
