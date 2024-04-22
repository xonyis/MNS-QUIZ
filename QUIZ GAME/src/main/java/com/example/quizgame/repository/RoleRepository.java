package com.example.quizgame.repository;

import com.example.quizgame.model.EnumRole;
import com.example.quizgame.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}