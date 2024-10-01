package com.hackathon.hackathonbackend.controllers;

import com.hackathon.hackathonbackend.constants.Headers;
import com.hackathon.hackathonbackend.dtos.hackathonmanage.*;
import com.hackathon.hackathonbackend.enums.UserRole;
import com.hackathon.hackathonbackend.services.AuthenticationServices;
import com.hackathon.hackathonbackend.services.HackathonServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hackathons")
@CrossOrigin("http://localhost:4200")
@Validated
public class HackathonController {
    @Autowired
    private HackathonServices hackathonService;

    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping
    public ResponseEntity<HackathonInfoResponse> createHackathon(@RequestHeader(name = Headers.AUTHORIZATION) String token,
                                                                 @Valid @RequestBody HackathonCreateRequest request) {
        if(!authenticationServices.authenticate(token, UserRole.ADMIN)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(hackathonService.createHackathon(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HackathonInfoResponse> updateHackathon(@RequestHeader(name = Headers.AUTHORIZATION) String token, @PathVariable Long id, @Valid @RequestBody HackathonUpdateRequest request) {
        if(!authenticationServices.authenticate(token, UserRole.ADMIN)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(hackathonService.updateHackathon(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{hackathonId}")
    public ResponseEntity<Void> deleteHackathon(@RequestHeader(name = Headers.AUTHORIZATION) String token, @PathVariable Long hackathonId) {
        if(!authenticationServices.authenticate(token, UserRole.ADMIN)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        hackathonService.deleteHackathon(hackathonId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<GetAllHackathonsResponse> getAllHackathons() {
        return ResponseEntity.ok(hackathonService.getAllHackathons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HackathonFullDetailsInfoResponse> getHackathonFullDetails(@RequestHeader(name = Headers.AUTHORIZATION) String token,
                                                                                    @PathVariable Long id) {
        if(!authenticationServices.authenticate(token, UserRole.ADMIN)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(hackathonService.getHackathonFullDetails(id));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<HackathonDetailsInfoResponse> getHackathonDetails(@RequestHeader(name = Headers.AUTHORIZATION) String token,
                                                                            @PathVariable Long id) {
        if(!authenticationServices.authenticate(token, UserRole.USER)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(hackathonService.getHackathonDetails(id));
    }

    @PostMapping("/{hackathonId}/teams/register")
    public ResponseEntity<TeamRegistrationResponse> registerTeam(@RequestHeader(name = Headers.AUTHORIZATION) String token,
                                                                 @PathVariable Long hackathonId,
                                                                 @Valid @RequestBody TeamRegistrationRequest request) {
        if(!authenticationServices.authenticate(token, UserRole.USER)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        TeamRegistrationResponse response = hackathonService.registerTeam(hackathonId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
