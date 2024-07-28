package com.mfilaniu.web_project_backend.entity.movies;

import com.mfilaniu.web_project_backend.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "users_movies")
@NoArgsConstructor
@AllArgsConstructor
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public void addUser(User user) {
        this.user = user;
        user.getMovies().add(this);
    }

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getUsers().add(this);
    }

}
