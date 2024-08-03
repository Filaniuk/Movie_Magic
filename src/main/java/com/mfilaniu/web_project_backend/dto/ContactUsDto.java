package com.mfilaniu.web_project_backend.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class ContactUsDto {

    String fullname;

    String email;

    String textTitle;

    String textBody;

    MultipartFile attachedFile;

}
