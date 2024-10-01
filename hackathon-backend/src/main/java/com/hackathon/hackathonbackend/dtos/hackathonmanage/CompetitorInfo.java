package com.hackathon.hackathonbackend.dtos.hackathonmanage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitorInfo {
    @JsonProperty("name")
    @NotBlank(message = "Name must not be blank")
    private String name;

    @JsonProperty("title")
    @NotBlank(message = "Title must not be blank")
    private String title;

    @JsonProperty("email")
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Incorrect email format")
    private String email;

    @JsonProperty("personalId")
    @NotBlank(message = "Personal ID must not be blank")
    private String personalId;

    @JsonProperty("mobile")
    @NotBlank(message = "Mobile must not be blank")
    private String mobile;
}
