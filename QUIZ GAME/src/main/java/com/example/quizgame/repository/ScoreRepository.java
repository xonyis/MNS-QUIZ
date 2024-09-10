package com.example.quizgame.repository;

import com.example.quizgame.model.Quiz;
import com.example.quizgame.model.Score;
import com.example.quizgame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findByUserAndQuiz(User user, Quiz quiz);
}
