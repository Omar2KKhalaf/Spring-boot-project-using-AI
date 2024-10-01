package com.hackathon.hackathonbackend.dtos.hackathonmanage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllHackathonsResponse {
    @JsonProperty("hackathons")
    private List<HackathonInfoResponse> hackathons;
}
