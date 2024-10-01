package com.hackathon.hackathonbackend.exceptions.hackathon;

public class MaxTeamSizeException extends RuntimeException{
    public MaxTeamSizeException() {
        super("The number of members exceeds the maximum team size.");
    }
}
