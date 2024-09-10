package com.example.quizgame.controller;

import com.example.quizgame.model.Team;
import com.example.quizgame.repository.TeamRepository;
import com.example.quizgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, String> request) {
        System.out.println(request);
        String name = request.get("name");
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Team team = teamService.createTeam(name);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/delete/{teamId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> deleteTeam(@PathVariable long teamId) {

        teamService.deleteTeam(teamId);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/{teamId}/addUser/{userName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> addUserToTeam(@PathVariable Long teamId, @PathVariable String userName) {
        Team team = teamService.addUserToTeam(teamId, userName);
        return ResponseEntity.ok(team);
    }

    @PostMapping("/{teamId}/removeUser/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> removeUserFromTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        Team team = teamService.removeUserFromTeam(teamId, userId);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/teamByName/{teamName}")
    public ResponseEntity<Team> findTeamByName(@PathVariable String teamName) {
        Team team = teamRepository.findByName(teamName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        return ResponseEntity.ok(team);
    }

}
