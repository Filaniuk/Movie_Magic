package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.entity.movies.Movie;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MovieDTOToMovie implements Mapper<MovieDTO, Movie> {

    @Override
    public Movie mapFrom(MovieDTO movieDTO) {
        return Movie.builder()
                .title(movieDTO.getTitle())
                .voteAverage(Double.parseDouble(movieDTO.getVoteAverage()))
                .voteCount(movieDTO.getVoteCount())
                .popularity(Double.parseDouble(movieDTO.getPopularity()))
                .releaseDate(LocalDate.parse(movieDTO.getReleaseDate()))
                .posterLink(movieDTO.getLinkToImage())
                .overview(movieDTO.getOverview())
                .adult(movieDTO.isAdult())
                .build();
    }
}
