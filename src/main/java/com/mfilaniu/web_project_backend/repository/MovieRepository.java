package com.mfilaniu.web_project_backend.repository;

import com.mfilaniu.web_project_backend.entity.movies.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Movie save(Movie movie);

    public Optional<Movie> findByTitleAndReleaseDate(String title, LocalDate releaseDate);

    public boolean existsByTitle(String title);

    public boolean existsByTitleAndReleaseDate(String title, LocalDate releaseDate);

}
