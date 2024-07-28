package com.mfilaniu.web_project_backend.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User with such Id is not found");
    }

}
