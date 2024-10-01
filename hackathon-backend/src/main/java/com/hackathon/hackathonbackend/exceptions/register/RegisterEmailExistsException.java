package com.hackathon.hackathonbackend.exceptions.register;

public class RegisterEmailExistsException extends RuntimeException {
    public RegisterEmailExistsException() {
        super("Email is already taken.");
    }
}
