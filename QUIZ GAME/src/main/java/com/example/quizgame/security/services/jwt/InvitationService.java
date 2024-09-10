package com.example.quizgame.security.services.jwt;

import java.util.UUID;

public class InvitationService {
    public String generateInvitationLink(String baseUrl) {
        String token = UUID.randomUUID().toString();
        return baseUrl + "/register?token=" + token;
    }
}
