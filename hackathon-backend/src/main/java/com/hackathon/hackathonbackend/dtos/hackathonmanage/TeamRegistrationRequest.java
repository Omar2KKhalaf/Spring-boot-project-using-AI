package com.hackathon.hackathonbackend.dtos.hackathonmanage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TeamRegistrationRequest {

    @JsonProperty("teamName")
    @NotBlank(message = "Team name must not be blank")
    private String teamName;

    @JsonProperty("selectedChallenge")
    @NotBlank(message = "Selected challenge must not be blank")
    private String selectedChallenge;

    @JsonProperty("competitors")
    @NotNull(message = "Competitors list must not be null")
    @Valid
    @Size(min = 1, message = "At least one competitor must be provided")
    private List<CompetitorInfo> competitors;
}
