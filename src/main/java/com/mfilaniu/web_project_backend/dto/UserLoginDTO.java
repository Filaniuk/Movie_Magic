package com.mfilaniu.web_project_backend.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserLoginDTO {

    String username;

    String password;

}
