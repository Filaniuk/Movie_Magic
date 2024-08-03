package com.mfilaniu.web_project_backend.exceptions;

public class ContactUsMessageFailedException extends Exception {

    public ContactUsMessageFailedException() {
        super("Message sending has failed");
    }

}
