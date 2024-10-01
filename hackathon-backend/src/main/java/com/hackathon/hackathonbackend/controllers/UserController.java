package com.hackathon.hackathonbackend.controllers;

import com.hackathon.hackathonbackend.dtos.login.LoginRequest;
import com.hackathon.hackathonbackend.dtos.login.LoginResponse;
import com.hackathon.hackathonbackend.dtos.register.RegisterRequest;
import com.hackathon.hackathonbackend.dtos.register.RegisterResponse;
import com.hackathon.hackathonbackend.services.UserAuthServices;
import com.hackathon.hackathonbackend.services.implementation.UserAuthServicesImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
@Validated
public class UserController {

    @Autowired
    private UserAuthServices authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}