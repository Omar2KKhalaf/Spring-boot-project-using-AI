package com.hackathon.hackathonbackend.services.implementation;

import com.hackathon.hackathonbackend.enums.UserRole;
import com.hackathon.hackathonbackend.models.User;
import com.hackathon.hackathonbackend.services.AuthenticationServices;
import com.hackathon.hackathonbackend.services.UserAuthServices;
import com.hackathon.hackathonbackend.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AuthenticationServicesImpl implements AuthenticationServices {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserAuthServices userAuthServices;

    @Override
    public boolean authenticate(String token, UserRole userRole) {
        try {
            Long userId = jwtUtil.extractUserId(token);
            Optional<User> optionalUser = userAuthServices.getUserById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

//                if ((jwtUtil.validateToken(token, user) && user.getRole().equals(userRole)) ||
//                        (jwtUtil.validateToken(token, user) && userRole == null)) {
//
//                    return true;
//
//                }


            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
