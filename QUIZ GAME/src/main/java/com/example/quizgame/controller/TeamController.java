package com.example.quizgame.controller;

import com.example.quizgame.model.Team;
import com.example.quizgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> createTeam(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Team team = teamService.createTeam(name);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/{teamId}/addUser/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> addUserToTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        Team team = teamService.addUserToTeam(teamId, userId);
        return ResponseEntity.ok(team);
    }
}
