package com.hackathon.hackathonbackend.exceptions.hackathon;

public class HackathonCreateException extends RuntimeException{
    public HackathonCreateException() {
        super("Error happen when creating Hackathon");
    }
}
