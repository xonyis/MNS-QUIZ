package com.example.quizgame.controller;

import com.example.quizgame.model.Roles;
import com.example.quizgame.model.Users;
import com.example.quizgame.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8889")
@RestController
@RequestMapping("/api")
public class RolesController {
    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> getAllRoles(@RequestParam(required = false) String roleName) {
        try {
            List<Roles> roles = new ArrayList<Roles>();

            if (roleName == null)
                rolesRepository.findAll().forEach(roles::add);
            else
                rolesRepository.findByRoleName(roleName).forEach(roles::add);

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<Roles> createRole(@RequestBody Roles role) {
        try {
            Roles _role = rolesRepository.save(new Roles(role.getRoleName()));
            return new ResponseEntity<>(_role, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
