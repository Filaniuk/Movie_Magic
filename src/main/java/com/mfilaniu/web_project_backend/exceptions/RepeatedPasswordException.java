package com.mfilaniu.web_project_backend.exceptions;

public class RepeatedPasswordException extends Exception{

    public RepeatedPasswordException(){
        super("Your passwords are not the same");
    }

}
