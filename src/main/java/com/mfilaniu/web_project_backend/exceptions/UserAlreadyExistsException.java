package com.mfilaniu.web_project_backend.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super("User with this username already exists");
    }

}
