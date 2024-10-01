package com.hackathon.hackathonbackend.repositories;

import com.hackathon.hackathonbackend.enums.UserRole;
import com.hackathon.hackathonbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByRole(UserRole role);
}
