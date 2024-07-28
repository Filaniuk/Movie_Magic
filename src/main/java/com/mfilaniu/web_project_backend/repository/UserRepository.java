package com.mfilaniu.web_project_backend.repository;

import com.mfilaniu.web_project_backend.entity.user.Citizenship;
import com.mfilaniu.web_project_backend.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //find

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"movies"})
    public Optional<User> findById(Long id);

    public List<User> findAll();

    public List<User> findByCitizenship(Citizenship citizenship);

    public Optional<User> findByUsernameAndPassword(String username, String password);

    //save + update method

    public User save(User user);

    //delete

    public void deleteByUsername(String username);

    public void deleteByEmail(String email);

    public void deleteById(Long id);

    //exists

    public boolean existsByUsername(String username);


}
