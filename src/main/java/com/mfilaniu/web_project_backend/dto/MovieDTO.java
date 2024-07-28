package com.mfilaniu.web_project_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

    Long movieId;
    String title;
    double voteAverage;
    int voteCount;
    double popularity;
    String releaseDate;
    String linkToImage;
    String overview;
    boolean adult;

}