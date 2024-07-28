package com.mfilaniu.web_project_backend.exceptions;

public class MovieNotFoundException extends Exception {

    public MovieNotFoundException() {
        super("User with such Id is not found");
    }

}