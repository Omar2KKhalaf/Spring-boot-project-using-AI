package com.hackathon.hackathonbackend.services.implementation;

import com.hackathon.hackathonbackend.dtos.login.LoginRequest;
import com.hackathon.hackathonbackend.dtos.login.LoginResponse;
import com.hackathon.hackathonbackend.dtos.register.RegisterRequest;
import com.hackathon.hackathonbackend.dtos.register.RegisterResponse;
import com.hackathon.hackathonbackend.enums.UserRole;
import com.hackathon.hackathonbackend.exceptions.login.LoginErrorException;
import com.hackathon.hackathonbackend.exceptions.register.RegisterEmailExistsException;
import com.hackathon.hackathonbackend.exceptions.register.RegisterErrorException;
import com.hackathon.hackathonbackend.models.User;
import com.hackathon.hackathonbackend.repositories.UserRepository;
import com.hackathon.hackathonbackend.services.UserAuthServices;
import com.hackathon.hackathonbackend.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServicesImpl implements UserAuthServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        // Validate Request
        // check email isn't exist before
        if(userRepository.findByEmail(request.getEmail()) != null) {
            throw new RegisterEmailExistsException();
        }
        try {
            // Create new user account
            User newUser = new User();
            newUser.setEmail(request.getEmail());
            // Encode password for security
            newUser.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
            newUser.setRole(UserRole.USER);
            userRepository.save(newUser);
            return new RegisterResponse(UserRole.USER.name());
        } catch (Exception e) {
            throw new RegisterErrorException();
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if(authentication.isAuthenticated()){
            User user = userRepository.findByEmail(request.getEmail());

            return new LoginResponse(jwtUtil.generateToken(user), user.getRole().name());
        }
        else {
            throw new LoginErrorException();
        }
//        try {
//            User user = userRepository.findByEmail(request.getEmail());
//            // Validate credentials
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            if(user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//                // Generate token (abstracted, implement based on your JWT library)
//                String token=jwtUtil.generateToken(user);
//
//                return new LoginResponse(token, user.getRole().name());
//            }
//        } catch (Exception e) {
//            throw new LoginErrorException();
//        }
//        throw new LoginUnauthException();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
