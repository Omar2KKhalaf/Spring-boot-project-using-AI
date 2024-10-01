package com.hackathon.hackathonbackend.repositories;

import com.hackathon.hackathonbackend.models.Hackathon;
import com.hackathon.hackathonbackend.models.HackathonTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HackathonTeamRepository extends JpaRepository<HackathonTeam, Long> {
    HackathonTeam findByTeamNameAndHackathon(String teamName, Hackathon hackathon);
}
