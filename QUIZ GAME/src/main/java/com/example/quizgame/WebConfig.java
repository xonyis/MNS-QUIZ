package com.example.quizgame;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173/") // Permet à toutes les origines d'accéder à votre API
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorise les méthodes HTTP
                .allowedHeaders("*"); // Autorise tous les en-têtes HTTP
    }
}