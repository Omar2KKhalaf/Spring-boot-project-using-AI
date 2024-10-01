package com.hackathon.hackathonbackend.services;

import com.hackathon.hackathonbackend.enums.UserRole;

public interface AuthenticationServices {
    public boolean authenticate(String token, UserRole userRole);
}
