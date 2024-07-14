package com.example.quizgame.controller;

import com.example.quizgame.dto.UserDto;
import com.example.quizgame.model.User;
import com.example.quizgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    
}
