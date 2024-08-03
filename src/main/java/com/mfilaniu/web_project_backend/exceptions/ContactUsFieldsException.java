package com.mfilaniu.web_project_backend.exceptions;

public class ContactUsFieldsException extends Exception {

    public ContactUsFieldsException() {
        super("Please, fill out all required fields.");
    }

}
