package com.example.quizgame.repository;

import com.example.quizgame.model.Quiz;
import com.example.quizgame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Query(value = "SELECT * FROM quiz q WHERE q.visibility = :visibility", nativeQuery = true)
    List<Quiz> findByIsPublic(@Param("visibility")  boolean isPublic);

    List<Quiz> findByTeamId(long teamId);
}
