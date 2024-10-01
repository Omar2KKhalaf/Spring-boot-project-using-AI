package com.hackathon.hackathonbackend.exceptions.hackathon;

public class HackathonUpdateException extends RuntimeException{
    public HackathonUpdateException() {
        super("Error happen when updating Hackathon");
    }
}
