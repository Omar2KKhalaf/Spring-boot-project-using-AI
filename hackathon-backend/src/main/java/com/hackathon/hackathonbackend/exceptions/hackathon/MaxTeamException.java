package com.hackathon.hackathonbackend.exceptions.hackathon;

public class MaxTeamException extends RuntimeException{
    public MaxTeamException() {
        super("The number of teams exceeds the maximum registered teams.");
    }
}
