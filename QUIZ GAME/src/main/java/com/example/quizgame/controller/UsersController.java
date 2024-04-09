package com.example.quizgame.controller;

import com.example.quizgame.model.Roles;
import com.example.quizgame.model.Users;
import com.example.quizgame.repository.RolesRepository;
import com.example.quizgame.repository.UsersRepository;

import com.example.quizgame.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8889")
@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UserService userService;


    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(@RequestParam(required = false) String email) {
        try {
            List<Users> users = new ArrayList<Users>();

            if (email == null)
                usersRepository.findAll().forEach(users::add);
            else
                usersRepository.findByEmail(email).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") long id) {
        Optional<Users> userData = usersRepository.findById(id);

        if (userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            Users _user = usersRepository.save(new Users(users.getLastName(), users.getEmail(), users.getFirstName()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users users) {
        Optional<Users> userData = usersRepository.findById(id);

        if (userData.isPresent()) {
            Users _user = userData.get();
            _user.setEmail(users.getEmail());
            _user.setFirstName(users.getFirstName());
            _user.setLastName(users.getLastName());
            return new ResponseEntity<>(usersRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
