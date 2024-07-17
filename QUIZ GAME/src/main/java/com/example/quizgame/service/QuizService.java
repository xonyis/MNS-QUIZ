package com.example.quizgame.service;

import com.example.quizgame.model.Quiz;
import com.example.quizgame.repository.ChoiceRepository;
import com.example.quizgame.repository.QuestionRepository;
import com.example.quizgame.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;
    public List<Quiz> getAllQuizzes() {
        System.out.println("--------------------------------");
        System.out.println(quizRepository.findAll());
        System.out.println("--------------------------------");
        return quizRepository.findAll();
    }
    public Quiz createQuiz(Quiz quiz) {
        System.out.println(quiz.toString());
        return quizRepository.save(quiz);
    }
}
