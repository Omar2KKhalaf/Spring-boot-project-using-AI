package com.hackathon.hackathonbackend.exceptions.hackathon;

public class HackathonNotFoundException extends RuntimeException{
    public HackathonNotFoundException(Long id) {
        super("Hackathon not found with ID: " + id);
    }
}
