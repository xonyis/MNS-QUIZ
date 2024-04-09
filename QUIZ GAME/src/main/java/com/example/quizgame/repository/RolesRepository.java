package com.example.quizgame.repository;

import com.example.quizgame.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Long> {
List<Roles> findByRoleName(String roleName);
}
