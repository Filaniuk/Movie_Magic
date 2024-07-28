package com.mfilaniu.web_project_backend.dto;

import com.mfilaniu.web_project_backend.entity.movies.Movie;
import com.mfilaniu.web_project_backend.entity.user.Citizenship;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class UserReadDTO {

    Long id;

    String username;

    String fullname;

    String email;

    LocalDate birthday;

    String avatarPath;

    Citizenship citizenship;

    List<MovieDTO> movies;


}
