package com.mfilaniu.web_project_backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfilaniu.web_project_backend.dto.MovieDTO;
import com.mfilaniu.web_project_backend.dto.MovieJson;
import com.mfilaniu.web_project_backend.entity.movies.Movie;
import com.mfilaniu.web_project_backend.entity.movies.UserMovie;
import com.mfilaniu.web_project_backend.exceptions.MovieNotFoundException;
import com.mfilaniu.web_project_backend.mapper.Mapper;
import com.mfilaniu.web_project_backend.repository.MovieRepository;
import com.mfilaniu.web_project_backend.repository.UserMoviesRepository;
import com.mfilaniu.web_project_backend.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Component
@Getter
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private static final String API_READ_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYjViMTc2YTU0NzY3ZDc4Y2U4ZGQ5ZGMyNTkyMDk3ZSIsIm5iZiI6MTcyMjA3MTQyMi43NTM2OTUsInN1YiI6IjY2YTRiOGNkNWU2ZWZhNGNlZTUyM2EzMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.GJMjeuaMVnsC5OHkE_nNnjcTGwv0vRMGODtWbbjUNiM";
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserMoviesRepository userMoviesRepository;
    private final Mapper<MovieDTO, Movie> mapperMovieDto;
    private final Mapper<Movie, MovieDTO> mapperMovieToMovieDto;
    private final Mapper<MovieJson, MovieDTO> mapperMovieJsonToMovieDto;

    private MovieResponse movieResponse;
    private int counter = 0;

    public MovieDTO findMovieById(Long movieId) throws MovieNotFoundException {
        return movieRepository.findById(movieId)
                .map(mapperMovieToMovieDto::mapFrom)
                .orElseThrow(MovieNotFoundException::new);
    }

    public MovieDTO generateMovie() {

        while (true) {
            if (movieResponse != null && counter < movieResponse.getResults().size()) {
                MovieDTO movieDTO =  mapperMovieJsonToMovieDto.mapFrom(movieResponse.getResults().get(counter++));
                System.out.println(movieDTO);
                return movieDTO;
            }
            counter = 0;
            movieResponse = getRandomInMemoryMovies();
        }

    }

    public boolean saveMovieToProfile(Long userId, MovieDTO movieDTO) {
        Movie movie;

        if (!movieRepository.existsByTitleAndReleaseDate(movieDTO.getTitle(),
                LocalDate.parse(movieDTO.getReleaseDate()))) {
            movie = movieRepository.save(mapperMovieDto.mapFrom(movieDTO));
            saveUserMovieToDB(userId, movie);
            return true;
        }
        else {
            movie = movieRepository.findByTitleAndReleaseDate(movieDTO.getTitle(),
                    LocalDate.parse(movieDTO.getReleaseDate())).get();
            if(userMoviesRepository.existsByUserIdAndMovieId(userId, movie.getId())) {
                return false;
            }
            saveUserMovieToDB(userId, movie);
            return true;
        }

    }

    private void saveUserMovieToDB(Long userId, Movie movie) {
        UserMovie userMovie = new UserMovie();
        userMovie.addMovie(movie);
        userMovie.addUser(userRepository.findById(userId).get());
        userMoviesRepository.save(userMovie);
    }

    private MovieResponse getRandomInMemoryMovies() {

        int page = ThreadLocalRandom.current().nextInt(1, 10);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=%d".formatted(page))
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer %s".formatted(API_READ_TOKEN))
                .build();

        try {
            Response response = client.newCall(request).execute();
            this.movieResponse = OBJECT_MAPPER.readValue(response.body().string(), MovieResponse.class);
            return movieResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
