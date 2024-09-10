package com.example.quizgame.controller;

import com.example.quizgame.dto.UserDto;
import com.example.quizgame.model.Team;
import com.example.quizgame.model.User;
import com.example.quizgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService service;
//todo : a foute dans le servie
    @GetMapping("/getall")
    public List<UserDto> getAllUsers() {
        List<User> users = service.getAllUsers();
        System.out.println(users);
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole().getName().name(),
                        user.getImg()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}/getTeam")
    public Set<Team> getTeam(@PathVariable long userId){
        return service.getTeamsOfUser(userId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {

        service.deleteUser(id);
        return ResponseEntity.ok(null);
    }

}
