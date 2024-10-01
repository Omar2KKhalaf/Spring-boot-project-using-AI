package com.hackathon.hackathonbackend.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email cannot be blank")
    @JsonProperty("email")
    @Email(message = "Incorrect email format")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;

}
