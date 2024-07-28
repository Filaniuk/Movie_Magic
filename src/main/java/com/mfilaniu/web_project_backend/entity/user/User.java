package com.mfilaniu.web_project_backend.entity.user;


import com.mfilaniu.web_project_backend.entity.movies.UserMovie;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(exclude = "movies")
@ToString(exclude = "movies")
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String fullname;

    private String password;

    private String email;

    private LocalDate birthdate;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Enumerated(EnumType.STRING)
    private Citizenship citizenship;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserMovie> movies = new ArrayList<>();


}
