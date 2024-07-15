package com.example.quizgame.config;

import com.example.quizgame.enums.EnumRole;
import com.example.quizgame.model.Role;
import com.example.quizgame.model.User;
import com.example.quizgame.repository.RoleRepository;
import com.example.quizgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDataLoader {

    @Autowired
    PasswordEncoder encoder;

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            // Check if roles exist, if not create them
            if (!roleRepository.existsByName(EnumRole.ROLE_ADMIN)) {
                Role adminRole = new Role(EnumRole.ROLE_ADMIN);
                roleRepository.save(adminRole);
            }

            if (!roleRepository.existsByName(EnumRole.ROLE_USER)) {
                Role userRole = new Role(EnumRole.ROLE_USER);
                roleRepository.save(userRole);
            }

            // Check if admin user exists, if not create it
            if (!userRepository.existsByUsername("admin")) {
                Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword(encoder.encode("admin123"));
                adminUser.setRole(adminRole); // Set the single role

                userRepository.save(adminUser);
            }

            // Check if admin user exists, if not create it
            if (!userRepository.existsByUsername("user")) {
                Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                User userUser = new User();
                userUser.setUsername("user");
                userUser.setEmail("user@example.com");
                userUser.setPassword(encoder.encode("user123"));
                userUser.setRole(userRole); // Set the single role

                userRepository.save(userUser);
            }
        };
    }
}