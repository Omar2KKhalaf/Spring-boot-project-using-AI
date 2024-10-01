package com.hackathon.hackathonbackend.dtos.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email cannot be blank")
    @JsonProperty("email")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    @Size(min = 5, message = "Password cannot be less than 5 characters")
    private String password;
}
