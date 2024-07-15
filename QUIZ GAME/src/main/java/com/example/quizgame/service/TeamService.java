package com.example.quizgame.service;

import com.example.quizgame.model.Team;
import com.example.quizgame.model.User;
import com.example.quizgame.repository.TeamRepository;
import com.example.quizgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public Team createTeam(String name) {
        Team team = new Team(name);
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Team addUserToTeam(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        team.getUsers().add(user);
        user.getTeams().add(team);

        userRepository.save(user);
        return teamRepository.save(team);
    }
}
