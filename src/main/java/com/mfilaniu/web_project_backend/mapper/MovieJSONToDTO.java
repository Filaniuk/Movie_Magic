package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.dto.MovieJson;
import org.springframework.stereotype.Component;

@Component
public class MovieJSONToDTO implements Mapper<MovieJson, MovieDTO>{

    @Override
    public MovieDTO mapFrom(MovieJson movieJson) {
        return MovieDTO.builder()
                .title(movieJson.getTitle())
                .voteAverage(String.format("%.2f", movieJson.getVoteAverage()).replace(',', '.'))
                .voteCount(movieJson.getVoteCount())
                .popularity(String.format("%.1f", movieJson.getPopularity()).replace(',', '.'))
                .releaseDate(movieJson.getReleaseDate())
                .linkToImage(movieJson.getLinkToImage())
                .overview(movieJson.getOverview())
                .adult(movieJson.isAdult())
                .build();
    }
}
