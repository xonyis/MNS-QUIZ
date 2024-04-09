package com.example.quizgame.service;

import com.example.quizgame.model.Users;
import com.example.quizgame.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users user) {
        // Logique pour créer un nouvel utilisateur
        return usersRepository.save(user);
    }

    public Users getUserByEmail(String email) {
        // Logique pour récupérer un utilisateur par son email
        return (Users) usersRepository.findByEmail(email);
    }
}
