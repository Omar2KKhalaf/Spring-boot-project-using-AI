package com.hackathon.hackathonbackend.dtos.hackathonmanage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HackathonUpdateRequest {
    @NotBlank(message = "Name is required")
    @JsonProperty("name")
    private String name;
    @NotBlank(message = "Theme is required")
    @JsonProperty("theme")
    private String theme;
    @NotNull(message = "Registration start date is required")
    @JsonProperty("registrationStartDate")
    private LocalDate registrationStartDate;
    @NotNull(message = "Registration end date is required")
    @JsonProperty("registrationEndDate")
    private LocalDate registrationEndDate;
    @NotNull(message = "Event date is required")
    @JsonProperty("eventDate")
    private LocalDate eventDate;
    @NotNull(message = "Challenge titles are required")
    @Size(min = 1, message = "At least one challenge title must be provided")
    @JsonProperty("challengeTitles")
    private List<String> challengeTitles;
    @NotNull(message = "Max team size is required")
    @JsonProperty("maxTeamSize")
    private Integer maxTeamSize;
    @NotNull(message = "Max teams is required")
    @JsonProperty("maxTeams")
    private Integer maxTeams;
}