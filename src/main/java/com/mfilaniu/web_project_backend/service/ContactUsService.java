package com.mfilaniu.web_project_backend.service;

import com.mfilaniu.web_project_backend.dto.ContactUsDto;
import com.mfilaniu.web_project_backend.exceptions.ContactUsFieldsException;
import com.mfilaniu.web_project_backend.exceptions.ContactUsMessageFailedException;
import com.mfilaniu.web_project_backend.service.util.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {

    public void sendEmailMessage(ContactUsDto contactUsDto)
            throws ContactUsFieldsException, ContactUsMessageFailedException {

        validateDetails(contactUsDto);

        EmailSender emailSender = new EmailSender(
                contactUsDto.getEmail(),
                contactUsDto.getFullname(),
                contactUsDto.getTextTitle(),
                contactUsDto.getTextBody(),
                contactUsDto.getAttachedFile()
        );

        try {
            emailSender.sendEmail();
        } catch (Exception e) {
            throw new ContactUsMessageFailedException();
        }

    }

    private void validateDetails(ContactUsDto contactUsDto) throws ContactUsFieldsException {
        if (contactUsDto == null ||
            contactUsDto.getEmail() == null ||
            contactUsDto.getEmail().trim().isEmpty() ||
            contactUsDto.getFullname() == null ||
            contactUsDto.getFullname().trim().isEmpty() ||
            contactUsDto.getTextTitle() == null ||
            contactUsDto.getTextTitle().trim().isEmpty() ||
            contactUsDto.getTextBody() == null ||
            contactUsDto.getTextBody().trim().isEmpty()) {
            throw new ContactUsFieldsException();
        }
    }

}
