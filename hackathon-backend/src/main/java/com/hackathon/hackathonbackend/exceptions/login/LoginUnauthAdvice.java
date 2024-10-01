package com.hackathon.hackathonbackend.exceptions.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginUnauthAdvice {
    @ResponseBody
    @ExceptionHandler(LoginUnauthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> exceptionHandler(LoginUnauthException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }
}
