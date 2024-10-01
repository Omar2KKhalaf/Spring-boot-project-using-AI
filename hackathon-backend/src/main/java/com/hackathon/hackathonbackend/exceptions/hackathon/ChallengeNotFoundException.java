package com.hackathon.hackathonbackend.exceptions.hackathon;

public class ChallengeNotFoundException extends RuntimeException{
    public ChallengeNotFoundException() {
        super("Selected Challenge isn't found.");
    }
}
