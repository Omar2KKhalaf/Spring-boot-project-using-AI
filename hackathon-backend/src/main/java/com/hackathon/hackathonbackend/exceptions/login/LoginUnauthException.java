package com.hackathon.hackathonbackend.exceptions.login;

public class LoginUnauthException extends RuntimeException {
    public LoginUnauthException() {
        super("Invalid credentials");
    }
}
