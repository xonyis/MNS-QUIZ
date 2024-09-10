package com.example.quizgame.controller;

import com.example.quizgame.model.Quiz;
import com.example.quizgame.model.Score;
import com.example.quizgame.model.Team;
import com.example.quizgame.repository.QuizRepository;
import com.example.quizgame.repository.TeamRepository;
import com.example.quizgame.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private QuizService service;
    @GetMapping("/all")
    public List<Quiz> getAllQuizzes() {
        return service.getAllQuizzes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable int id) {
        return quizRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createQuizz")
    public Quiz createQuiz(@RequestBody Quiz quiz, @RequestParam(required = false) String teamName) {

//        {
//    "title": "HTML",
//    "questions": [
//        {
//            "text": "Html est un language de :",
//            "type": "REGULAR",
//            "choices": [
//                {"text": "balisage", "isCorrect": true},
//                {"text": "programmation", "isCorrect": false},
//                {"text": "style", "isCorrect": false},
//                {"text": "scripting", "isCorrect": false}
//            ]
//        },
//        {
//            "text": "Quelle est la balise correcte pour un lien hypertexte ?",
//            "type": "REGULAR",
//            "choices": [
//                {"text": "<a>", "isCorrect": true},
//                {"text": "<link>", "isCorrect": false},
//                {"text": "<href>", "isCorrect": false},
//                {"text": "<url>", "isCorrect": false}
//            ]
//        },
//        {
//            "text": "Quelle balise HTML est utilisée pour définir une liste non ordonnée ?",
//            "type": "REGULAR",
//            "choices": [
//                {"text": "<ul>", "isCorrect": true},
//                {"text": "<ol>", "isCorrect": false},
//                {"text": "<li>", "isCorrect": false},
//                {"text": "<list>", "isCorrect": false}
//            ]
//        },
//        {
//            "text": "Quelle est la balise correcte pour insérer une ligne horizontale ?",
//            "type": "REGULAR",
//            "choices": [
//                {"text": "<hr>", "isCorrect": true},
//                {"text": "<line>", "isCorrect": false},
//                {"text": "<br>", "isCorrect": false},
//                {"text": "<hline>", "isCorrect": false}
//            ]
//        },
//        {
//            "text": "Quel attribut HTML est utilisé pour définir le texte alternatif pour une image ?",
//            "type": "REGULAR",
//            "choices": [
//                {"text": "alt", "isCorrect": true},
//                {"text": "title", "isCorrect": false},
//                {"text": "src", "isCorrect": false},
//                {"text": "href", "isCorrect": false}
//            ]
//        }
//    ]
//}
        Optional<Team> team = teamRepository.findByName(teamName);
        if (team.isPresent()) {
            return service.createQuiz(quiz, team.get().getId());
        }
        return service.createQuiz(quiz, null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable int id, @RequestBody Quiz quizDetails) {
        return quizRepository.findById(id)
                .map(quiz -> {
                    quiz.setTitle(quizDetails.getTitle());
                    if (quizDetails.getQuestions() != null) {
                        // Vider les anciennes questions
                        quiz.getQuestions().clear();

                        // Ajouter les nouvelles questions
                        quiz.getQuestions().addAll(quizDetails.getQuestions());
                    }
                    Quiz updatedQuiz = quizRepository.save(quiz);
                    return ResponseEntity.ok(updatedQuiz);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable int id) {
        return quizRepository.findById(id)
                .map(quiz -> {
                    quizRepository.delete(quiz);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/public")
    public List<Quiz> getPublicQuizzes() {
        return service.getPublicQuizzes();
    }

    @GetMapping("/{teamId}/findQuizByTeamId")
    public List<Quiz> getQuizzesByTeam(@PathVariable long teamId) {
        return service.getQuizByTeamId(teamId);
    }

    @PostMapping("/{quizId}/updateScore")
    public ResponseEntity<Score> updateScore(@RequestParam int userId, @PathVariable Long quizId, @RequestParam String score) {
        Score updatedScore = service.updateScore(userId, quizId, score);
        return ResponseEntity.ok(updatedScore);
    }
}
