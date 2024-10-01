package com.hackathon.hackathonbackend.repositories;

import com.hackathon.hackathonbackend.models.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
}
