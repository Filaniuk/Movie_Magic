package com.mfilaniu.web_project_backend.mapper;

import com.mfilaniu.web_project_backend.dto.UserUpdateDTO;
import com.mfilaniu.web_project_backend.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateDTOToUser implements Mapper<UserUpdateDTO, User> {

    @Override
    public User mapFrom(UserUpdateDTO userUpdateDTO) {
        return User.builder()
                .username(userUpdateDTO.getUsername())
                .password(userUpdateDTO.getPassword())
                .email(userUpdateDTO.getEmail())
                .avatarPath(savePictureAndGetLink(userUpdateDTO))
                .citizenship(userUpdateDTO.getCitizenship())
                .fullname(userUpdateDTO.getFullname())
                .birthdate(userUpdateDTO.getBirthdate())
                .build();
    }

    private String savePictureAndGetLink(UserUpdateDTO userUpdateDTO) {
        if(userUpdateDTO.getAvatar() == null) {return null;}
        return userUpdateDTO.getAvatar().getOriginalFilename();
    }
}
