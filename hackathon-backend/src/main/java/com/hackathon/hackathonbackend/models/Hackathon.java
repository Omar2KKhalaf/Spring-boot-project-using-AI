package com.hackathon.hackathonbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hackathon_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "theme")
    private String theme;

    @ElementCollection
    @Column(name = "challenge_title")
    private List<String> challengeTitles;

    @Column(name = "registration_start_date")
    private LocalDate registrationStartDate;

    @Column(name = "registration_end_date")
    private LocalDate registrationEndDate;

    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "max_team_size")
    private Integer maxTeamSize;

    @Column(name = "max_teams")
    private Integer maxTeams;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HackathonTeam> teams;

}