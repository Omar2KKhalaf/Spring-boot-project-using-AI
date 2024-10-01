package com.hackathon.hackathonbackend.dtos.hackathonmanage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HackathonDetailsInfoResponse {
    @JsonProperty("hackathonId")
    private Long hackathonId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("theme")
    private String theme;
    @JsonProperty("registrationStartDate")
    private LocalDate registrationStartDate;
    @JsonProperty("registrationEndDate")
    private LocalDate registrationEndDate;
    @JsonProperty("eventDate")
    private LocalDate eventDate;
    @JsonProperty("challengeTitles")
    private List<String> challengeTitles;
    @JsonProperty("maxTeamSize")
    private Integer maxTeamSize;
    @JsonProperty("maxTeams")
    private Integer maxTeams;
}
