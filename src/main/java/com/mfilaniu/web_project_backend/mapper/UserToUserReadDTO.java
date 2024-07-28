package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.dto.UserReadDTO;
import com.mfilaniu.web_project_backend.entity.movies.Movie;
import com.mfilaniu.web_project_backend.entity.user.User;
import com.mfilaniu.web_project_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserToUserReadDTO implements Mapper <User, UserReadDTO> {

    private final MovieService movieService;
    private final Mapper<Movie, MovieDTO> mapperMovieDto;

    @Override
    public UserReadDTO mapFrom(User user) {
        return UserReadDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .citizenship(user.getCitizenship())
                .birthday(user.getBirthdate())
                .fullname(user.getFullname())
                .avatarPath(user.getAvatarPath())
                .movies(findUserMovies(user))
                .build();
    }

    private List<MovieDTO> findUserMovies(User user) {
        List<MovieDTO> movies = new ArrayList<>();

        for (var userMovie : user.getMovies()) {
            movies.add(mapperMovieDto.mapFrom(userMovie.getMovie()));
        }

        return movies;
    }

}
