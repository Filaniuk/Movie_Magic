package com.mfilaniu.web_project_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieJson {

    private Long movieId;
    private String title;
    private double voteAverage;
    private int voteCount;
    private double popularity;
    private String releaseDate;
    private String linkToImage;
    private String overview;
    private boolean adult;

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("vote_count")
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @JsonProperty("popularity")
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("vote_average")
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("poster_path")
    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    @JsonProperty("overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty("adult")
    public void set(boolean adult) {
        this.adult = adult;
    }

}