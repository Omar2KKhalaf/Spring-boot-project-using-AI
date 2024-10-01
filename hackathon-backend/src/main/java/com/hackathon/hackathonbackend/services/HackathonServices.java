package com.hackathon.hackathonbackend.services;

import com.hackathon.hackathonbackend.dtos.hackathonmanage.*;

public interface HackathonServices {
    HackathonInfoResponse createHackathon(HackathonCreateRequest request);
    HackathonInfoResponse updateHackathon(Long hackathonId, HackathonUpdateRequest request);
    void deleteHackathon(Long hackathonId);
    GetAllHackathonsResponse getAllHackathons();
    HackathonFullDetailsInfoResponse getHackathonFullDetails(Long hackathonId);
    HackathonDetailsInfoResponse getHackathonDetails(Long hackathonId);
    TeamRegistrationResponse registerTeam(Long hackathonId, TeamRegistrationRequest request) ;
}
