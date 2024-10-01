package com.hackathon.hackathonbackend.exceptions.hackathon;

public class HackathonTeamNameException extends RuntimeException{
    public HackathonTeamNameException() {
        super("Hackathon Team Name exists before.");
    }
}
