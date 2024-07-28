package com.mfilaniu.web_project_backend.repository;

import com.mfilaniu.web_project_backend.entity.movies.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoviesRepository extends JpaRepository<UserMovie, Long> {

    public UserMovie save(UserMovie userMovie);

    public void deleteByUserIdAndMovieId(Long userId, Long movieId);

    public boolean existsByUserIdAndMovieId(Long userId, Long movieId);

}
