package com.mfilaniu.web_project_backend.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*@ControllerAdvice */ //TODO: TURN ON
public class GlobalExceptionHandler {

    /*@ExceptionHandler(Exception.class)*/
    public String exceptionHandler(Exception ex){

        return "error";
    }

}