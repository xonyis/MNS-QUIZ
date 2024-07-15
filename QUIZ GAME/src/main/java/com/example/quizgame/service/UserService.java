package com.example.quizgame.service;

import com.example.quizgame.model.Team;
import com.example.quizgame.model.User;
import com.example.quizgame.payload.response.UserInfoResponse;
import com.example.quizgame.repository.UserRepository;
import com.example.quizgame.security.services.UserDetailsImpl;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<UserInfoResponse> currentUser(){
        // Obtenez l'objet d'authentification de Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Vérifiez si l'utilisateur est authentifié
        if (authentication != null && authentication.isAuthenticated()) {

            // Obtenez les détails de l'utilisateur authentifié
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            // Construisez la réponse avec les informations de l'utilisateur
            UserInfoResponse userInfoResponse = new UserInfoResponse(userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getAuthorities());
            System.out.println(userInfoResponse);
            System.out.println(userDetails);

            // Renvoyez les informations de l'utilisateur dans la réponse
            return ResponseEntity.ok(userInfoResponse);
        } else {
            // Si l'utilisateur n'est pas authentifié, renvoyez une réponse avec un statut non autorisé
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    public User createUser(String username, String email, String password) {
        // Vérifiez si l'utilisateur existe déjà
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already in use!");
        }

        // Créez un nouvel utilisateur
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        // Encodez le mot de passe avant de le sauvegarder
        user.setPassword(passwordEncoder.encode(password));

        // Enregistrez l'utilisateur dans la base de données
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        // Mettez à jour les informations de l'utilisateur dans la base de données
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        // Supprimez l'utilisateur de la base de données
        userRepository.deleteById(userId);
    }

    public Set<Team> getTeamsOfUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTeams();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
