package com.mfilaniu.web_project_backend.service.util;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mfilaniu.web_project_backend.dto.MovieJson;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {
    private int page;
    private List<MovieJson> results;
    private int totalPages;
    private int totalResults;

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("results")
    public void setResults(List<MovieJson> results) {
        this.results = results;
    }

}