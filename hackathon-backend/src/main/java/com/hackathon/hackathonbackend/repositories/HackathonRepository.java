package com.hackathon.hackathonbackend.repositories;

import com.hackathon.hackathonbackend.models.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
}
