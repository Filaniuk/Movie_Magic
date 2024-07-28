package com.mfilaniu.web_project_backend.dto;

import com.mfilaniu.web_project_backend.entity.user.Citizenship;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
public class UserUpdateDTO {

    String username;

    String fullname;

    String email;

    String password;

    String repeatedPassword;

    LocalDate birthdate;

    MultipartFile avatar;

    Citizenship citizenship;

}
