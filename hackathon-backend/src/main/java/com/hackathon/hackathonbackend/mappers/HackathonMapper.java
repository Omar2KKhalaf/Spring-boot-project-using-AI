package com.hackathon.hackathonbackend.mappers;

import com.hackathon.hackathonbackend.dtos.hackathonmanage.*;
import com.hackathon.hackathonbackend.models.Hackathon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HackathonMapper {
    public HackathonInfoResponse createAndUpdateHackathonResponseMapping(Hackathon savedHackathon) {
        HackathonInfoResponse response = new HackathonInfoResponse();
        response.setHackathonId(savedHackathon.getId());
        response.setName(savedHackathon.getName());
        response.setTheme(savedHackathon.getTheme());
        response.setRegistrationStartDate(savedHackathon.getRegistrationStartDate());
        response.setRegistrationEndDate(savedHackathon.getRegistrationEndDate());
        response.setEventDate(savedHackathon.getEventDate());
        response.setChallengeTitles(savedHackathon.getChallengeTitles());
        response.setMaxTeamSize(savedHackathon.getMaxTeamSize());
        response.setMaxTeams(savedHackathon.getMaxTeams());
        return response;
    }

    public List<HackathonInfoResponse> getAllHackathonsResponseMapping(List<Hackathon> hackathons) {
        List<HackathonInfoResponse> responses = new ArrayList<>();
        hackathons.forEach((hackathon -> {
            HackathonInfoResponse response = new HackathonInfoResponse();
            response.setHackathonId(hackathon.getId());
            response.setName(hackathon.getName());
            response.setTheme(hackathon.getTheme());
            response.setRegistrationStartDate(hackathon.getRegistrationStartDate());
            response.setRegistrationEndDate(hackathon.getRegistrationEndDate());
            response.setEventDate(hackathon.getEventDate());
            response.setChallengeTitles(hackathon.getChallengeTitles());
            response.setMaxTeamSize(hackathon.getMaxTeamSize());
            response.setMaxTeams(hackathon.getMaxTeams());
            responses.add(response);
        }));
        return responses;
    }

    public HackathonFullDetailsInfoResponse getHackathonFullDetailsResponseMapping(Hackathon hackathon) {
        HackathonFullDetailsInfoResponse response = new HackathonFullDetailsInfoResponse();
        response.setHackathonId(hackathon.getId());
        response.setName(hackathon.getName());
        response.setTheme(hackathon.getTheme());
        response.setRegistrationStartDate(hackathon.getRegistrationStartDate());
        response.setRegistrationEndDate(hackathon.getRegistrationEndDate());
        response.setEventDate(hackathon.getEventDate());
        response.setChallengeTitles(hackathon.getChallengeTitles());
        response.setMaxTeamSize(hackathon.getMaxTeamSize());
        response.setMaxTeams(hackathon.getMaxTeams());
        response.setRegisteredTeams(hackathon.getTeams().size());
        List<TeamInfo> teamsInfo = hackathon.getTeams().stream()
                .map(team -> new TeamInfo(team.getTeamName(), team.getSelectedChallenge(),
                        team.getCompetitors().stream()
                                .map(competitor -> new CompetitorInfo(competitor.getName(), competitor.getTitle(), competitor.getEmail(), competitor.getPersonalId(), competitor.getMobile()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        response.setTeams(teamsInfo);
        return response;
    }

    public HackathonDetailsInfoResponse getHackathonDetailsResponseMapping(Hackathon hackathon) {
        HackathonDetailsInfoResponse response = new HackathonDetailsInfoResponse();
        response.setHackathonId(hackathon.getId());
        response.setName(hackathon.getName());
        response.setTheme(hackathon.getTheme());
        response.setRegistrationStartDate(hackathon.getRegistrationStartDate());
        response.setRegistrationEndDate(hackathon.getRegistrationEndDate());
        response.setEventDate(hackathon.getEventDate());
        response.setChallengeTitles(hackathon.getChallengeTitles());
        response.setMaxTeamSize(hackathon.getMaxTeamSize());
        response.setMaxTeams(hackathon.getMaxTeams());
        return response;
    }
}
