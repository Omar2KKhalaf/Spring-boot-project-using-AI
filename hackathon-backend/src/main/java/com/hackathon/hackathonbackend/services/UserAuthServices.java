package com.hackathon.hackathonbackend.services;

import com.hackathon.hackathonbackend.dtos.login.LoginRequest;
import com.hackathon.hackathonbackend.dtos.login.LoginResponse;
import com.hackathon.hackathonbackend.dtos.register.RegisterRequest;
import com.hackathon.hackathonbackend.dtos.register.RegisterResponse;
import com.hackathon.hackathonbackend.models.User;

import java.util.Optional;

public interface UserAuthServices {
    RegisterResponse registerUser(RegisterRequest request);
    LoginResponse login(LoginRequest request);

    public Optional<User> getUserById(Long id);
    }
