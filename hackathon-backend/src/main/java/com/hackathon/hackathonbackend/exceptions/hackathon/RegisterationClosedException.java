package com.hackathon.hackathonbackend.exceptions.hackathon;

public class RegisterationClosedException extends RuntimeException{
    public RegisterationClosedException() {
        super("Registration is not open. Please check the registration date range.");
    }
}
