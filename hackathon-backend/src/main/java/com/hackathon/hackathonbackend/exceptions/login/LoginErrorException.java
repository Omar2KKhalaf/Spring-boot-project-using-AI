package com.hackathon.hackathonbackend.exceptions.login;

public class LoginErrorException extends RuntimeException {
    public LoginErrorException() {
        super("Login Error Exception");
    }
}
