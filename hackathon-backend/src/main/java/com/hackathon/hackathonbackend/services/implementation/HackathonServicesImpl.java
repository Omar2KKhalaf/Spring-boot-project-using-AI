package com.hackathon.hackathonbackend.services.implementation;

import com.hackathon.hackathonbackend.dtos.hackathonmanage.*;
import com.hackathon.hackathonbackend.exceptions.hackathon.*;
import com.hackathon.hackathonbackend.mappers.HackathonMapper;
import com.hackathon.hackathonbackend.models.Competitor;
import com.hackathon.hackathonbackend.models.Hackathon;
import com.hackathon.hackathonbackend.models.HackathonTeam;
import com.hackathon.hackathonbackend.repositories.CompetitorRepository;
import com.hackathon.hackathonbackend.repositories.HackathonRepository;
import com.hackathon.hackathonbackend.repositories.HackathonTeamRepository;
import com.hackathon.hackathonbackend.services.HackathonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class HackathonServicesImpl implements HackathonServices {
    @Autowired
    private HackathonRepository hackathonRepository;
    @Autowired
    private HackathonTeamRepository hackathonTeamRepository;
    @Autowired
    private CompetitorRepository competitorRepository;
    @Autowired
    private HackathonMapper hackathonMapper;

    @Override
    @Transactional
    public HackathonInfoResponse createHackathon(HackathonCreateRequest request) {
        try {
            Hackathon hackathon = new Hackathon();
            hackathon.setName(request.getName());
            hackathon.setTheme(request.getTheme());
            hackathon.setRegistrationStartDate(request.getRegistrationStartDate());
            hackathon.setRegistrationEndDate(request.getRegistrationEndDate());
            hackathon.setEventDate(request.getEventDate());
            hackathon.setChallengeTitles(request.getChallengeTitles());
            hackathon.setMaxTeamSize(request.getMaxTeamSize());
            hackathon.setMaxTeams(request.getMaxTeams());

            Hackathon savedHackathon = hackathonRepository.save(hackathon);
            HackathonInfoResponse response = hackathonMapper.createAndUpdateHackathonResponseMapping(savedHackathon);
            return response;
        } catch (Exception e) {
            throw new HackathonCreateException();
        }
    }

    @Override
    @Transactional
    public HackathonInfoResponse updateHackathon(Long hackathonId, HackathonUpdateRequest request) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new HackathonNotFoundException(hackathonId));
        try {
            hackathon.setName(request.getName());
            hackathon.setTheme(request.getTheme());
            hackathon.setRegistrationStartDate(request.getRegistrationStartDate());
            hackathon.setRegistrationEndDate(request.getRegistrationEndDate());
            hackathon.setEventDate(request.getEventDate());
            hackathon.setChallengeTitles(request.getChallengeTitles());
            hackathon.setMaxTeamSize(request.getMaxTeamSize());
            hackathon.setMaxTeams(request.getMaxTeams());

            Hackathon updatedHackathon = hackathonRepository.save(hackathon);
            HackathonInfoResponse response = hackathonMapper.createAndUpdateHackathonResponseMapping(updatedHackathon);
            return response;
        } catch (Exception e) {
            throw new HackathonUpdateException();
        }
    }

    @Override
    @Transactional
    public void deleteHackathon(Long hackathonId) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(() -> new HackathonNotFoundException(hackathonId));
        hackathonRepository.delete(hackathon);
    }

    @Override
    @Transactional
    public GetAllHackathonsResponse getAllHackathons() {
        List<Hackathon> hackathons = hackathonRepository.findAll();
        GetAllHackathonsResponse response = new GetAllHackathonsResponse();
        if (hackathons == null || hackathons.isEmpty()) {
            return response;
        }
        List<HackathonInfoResponse> responses = hackathonMapper.getAllHackathonsResponseMapping(hackathons);
        response.setHackathons(responses);
        return response;
    }

    @Override
    @Transactional
    public HackathonFullDetailsInfoResponse getHackathonFullDetails(Long hackathonId) {
        // Validate Hackathon exists
        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new HackathonNotFoundException(hackathonId));
        HackathonFullDetailsInfoResponse response = hackathonMapper.getHackathonFullDetailsResponseMapping(hackathon);
        return response;
    }

    @Override
    @Transactional
    public HackathonDetailsInfoResponse getHackathonDetails(Long hackathonId) {
        // Validate Hackathon exists
        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new HackathonNotFoundException(hackathonId));
        HackathonDetailsInfoResponse response = hackathonMapper.getHackathonDetailsResponseMapping(hackathon);
        return response;
    }

    @Override
    @Transactional
    public TeamRegistrationResponse registerTeam(Long hackathonId, TeamRegistrationRequest request) {
        // Validate Hackathon exists
        Hackathon hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new HackathonNotFoundException(hackathonId));

        // Additional validation logic
        LocalDate today = LocalDate.now();
        if (today.isBefore(hackathon.getRegistrationStartDate()) || today.isAfter(hackathon.getRegistrationEndDate())) {
            throw new RegisterationClosedException();
        }

        if (hackathon.getMaxTeams() <= hackathon.getTeams().size()) {
            throw new MaxTeamException();
        }

        if (!hackathon.getChallengeTitles().contains(request.getSelectedChallenge())) {
            throw new ChallengeNotFoundException();
        }

        // Check that the number of team members does not exceed the maximum team size
        if (request.getCompetitors().size() > hackathon.getMaxTeamSize()) {
            throw new MaxTeamSizeException();
        }

        HackathonTeam hackathonTeam = hackathonTeamRepository.findByTeamNameAndHackathon(request.getTeamName(), hackathon);
        if (hackathonTeam != null) {
            throw new HackathonTeamNameException();
        }

        // Create and save team
        HackathonTeam team = new HackathonTeam();
        team.setTeamName(request.getTeamName());
        team.setSelectedChallenge(request.getSelectedChallenge());
        team.setHackathon(hackathon);
        team = hackathonTeamRepository.save(team);

        // Create and save competitors
        for (CompetitorInfo member : request.getCompetitors()) {
            Competitor competitor = new Competitor();
            competitor.setTeam(team);
            competitor.setName(member.getName());
            competitor.setEmail(member.getEmail());
            competitor.setMobile(member.getMobile());
            competitor.setPersonalId(member.getPersonalId());
            competitor.setTitle(member.getTitle());
            competitorRepository.save(competitor);
        }

        return new TeamRegistrationResponse("Team registered successfully");
    }
}
