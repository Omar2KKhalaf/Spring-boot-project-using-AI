package com.hackathon.hackathonbackend.dtos.hackathonmanage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfo {
    @JsonProperty("teamName")
    private String teamName;
    @JsonProperty("selectedChallenge")
    private String selectedChallenge;
    @JsonProperty("competitors")
    private List<CompetitorInfo> competitors;
}
