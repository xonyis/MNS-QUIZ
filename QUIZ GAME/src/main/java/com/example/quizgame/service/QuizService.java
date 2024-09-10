package com.example.quizgame.service;

import com.example.quizgame.model.Quiz;
import com.example.quizgame.model.Score;
import com.example.quizgame.model.Team;
import com.example.quizgame.model.User;
import com.example.quizgame.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ChoiceRepository choiceRepository;
    public List<Quiz> getAllQuizzes() {
        System.out.println("--------------------------------");
        System.out.println(quizRepository.findAll().toString());
        System.out.println("--------------------------------");
        return quizRepository.findAll();
    }
    public Quiz createQuiz(Quiz quiz, Long teamId) {


        if (teamId != null) {
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new RuntimeException("Équipe non trouvée"));
            quiz.setTeam(team);
        } else {
            quiz.setTeam(null); // Pas d'équipe associée
        }
        return quizRepository.save(quiz);
    }

    public List<Quiz> getPublicQuizzes() {
        return quizRepository.findByIsPublic(true); // Retourne les quiz publics
    }

    public List<Quiz> getQuizByTeamId(long teamId) {
        return quizRepository.findByTeamId(teamId);
    }
    public Score updateScore(int userId, Long quizId, String newScore) {
        User user = userRepository.findById((long) userId).orElseThrow(() -> new RuntimeException("User not found"));
        Quiz quiz = quizRepository.findById(Math.toIntExact(quizId)).orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Check if the user already has a score for the quiz
        Optional<Score> existingScore = scoreRepository.findByUserAndQuiz(user, quiz);

        if (existingScore.isPresent()) {
            // Update the existing score
            Score score = existingScore.get();
            score.setScore(newScore);
            return scoreRepository.save(score);
        } else {
            // Create a new score entry
            Score score = new Score();
            score.setUser(user);
            score.setQuiz(quiz);
            score.setScore(newScore);
            return scoreRepository.save(score);
        }
    }
}
