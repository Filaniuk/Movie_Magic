package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.UserCreateDTO;
import com.mfilaniu.web_project_backend.entity.user.User;
import com.mfilaniu.web_project_backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserCreateDTOToUser implements Mapper<UserCreateDTO, User> {

    private final ImageService imageService;

    @Override
    public User mapFrom(UserCreateDTO userCreateDTO) {
        return User.builder()
                .username(userCreateDTO.getUsername())
                .password(userCreateDTO.getPassword())
                .email(userCreateDTO.getEmail())
                .avatarPath(savePictureAndGetLink(userCreateDTO))
                .citizenship(userCreateDTO.getCitizenship())
                .fullname(userCreateDTO.getFullname())
                .birthdate(userCreateDTO.getBirthdate())
                .build();

    }

    private String savePictureAndGetLink(UserCreateDTO userCreateDTO) {
        if (userCreateDTO.getAvatar() == null || userCreateDTO.getAvatar().isEmpty()) {
            return null;
        }

        try {
            return imageService.uploadAndGetLink(userCreateDTO.getAvatar());
        } catch (IOException e) {
            throw new RuntimeException("Photo could not be uploaded");
        }
    }

}
