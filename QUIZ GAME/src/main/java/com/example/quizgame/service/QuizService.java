package com.example.quizgame.service;

import com.example.quizgame.model.Quiz;
import com.example.quizgame.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository repository;
    public List<Quiz> getAllQuizzes() {
        return repository.findAll();
    }
    public Quiz createQuiz(Quiz quiz) {
        return repository.save(quiz);
    }
}
