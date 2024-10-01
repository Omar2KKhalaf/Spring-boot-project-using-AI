package com.hackathon.hackathonbackend.initializer;

import com.hackathon.hackathonbackend.enums.UserRole;
import com.hackathon.hackathonbackend.models.User;
import com.hackathon.hackathonbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByRole(UserRole.ADMIN) == null) {
            User admin = new User();
            admin.setEmail("admin@ejada.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("12345"));
            admin.setRole(UserRole.ADMIN);
            userRepository.save(admin);
        }

    }
}
