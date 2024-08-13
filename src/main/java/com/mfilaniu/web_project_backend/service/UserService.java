package com.mfilaniu.web_project_backend.service;

import com.mfilaniu.web_project_backend.dto.UserCreateDTO;
import com.mfilaniu.web_project_backend.dto.UserLoginDTO;
import com.mfilaniu.web_project_backend.dto.UserReadDTO;
import com.mfilaniu.web_project_backend.dto.UserUpdateDTO;
import com.mfilaniu.web_project_backend.entity.user.User;
import com.mfilaniu.web_project_backend.exceptions.*;
import com.mfilaniu.web_project_backend.mapper.Mapper;
import com.mfilaniu.web_project_backend.repository.UserMoviesRepository;
import com.mfilaniu.web_project_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;
    private final Mapper<UserCreateDTO, User> userCreateDTOMapper;
    private final Mapper<User, UserReadDTO> userReadMapper;
    private final Mapper<UserUpdateDTO, User> userUpdateDTOMapper;
    private final UserMoviesRepository userMoviesRepository;

    public UserReadDTO save(UserCreateDTO userCreateDTO) throws UserAlreadyExistsException, NullPointerException, RepeatedPasswordException {

        if (userCreateDTO == null) {
            throw new NullPointerException();
        } else if (userRepository.existsByUsername(userCreateDTO.getUsername())) {
            throw new UserAlreadyExistsException();
        } else if (!userCreateDTO.getPassword().equals(userCreateDTO.getRepeatedPassword())) {
            throw new RepeatedPasswordException();
        }

        User savedUser = userRepository.save(
                userCreateDTOMapper.mapFrom(userCreateDTO));

        return userReadMapper.mapFrom(savedUser);
    }

    public UserReadDTO find(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .map(userReadMapper::mapFrom)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserReadDTO update(UserUpdateDTO userUpdateDTO) throws UserNotFoundException {

        //Validate exist ?
        if (userUpdateDTO == null
            || userUpdateDTO.getUsername() == null
            || !userRepository.existsByUsername(userUpdateDTO.getUsername())) {
            throw new UserNotFoundException();
        }

        User updatedUser = userRepository.save(
                userUpdateDTOMapper.mapFrom(userUpdateDTO));

        return userReadMapper.mapFrom(updatedUser);
    }

    public void delete(Long id) throws UserNotFoundException {

        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }


    public void deleteUserMovie(Long userId, Long movieId) throws MovieNotFoundException {
        userMoviesRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    public boolean isMovieInUserProfile(Long userId, Long movieId) {
        return userMoviesRepository.existsByUserIdAndMovieId(userId, movieId);
    }

    public UserReadDTO login(UserLoginDTO userLoginDTO) throws FalseLoginOrPasswordException {
        return userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword())
                .map(userReadMapper::mapFrom)
                .orElseThrow(FalseLoginOrPasswordException::new);
    }

}
