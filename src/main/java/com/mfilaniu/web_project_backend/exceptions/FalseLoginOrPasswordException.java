package com.mfilaniu.web_project_backend.exceptions;

public class FalseLoginOrPasswordException extends Exception{

    public FalseLoginOrPasswordException() {
        super("False credentials");
    }

}
