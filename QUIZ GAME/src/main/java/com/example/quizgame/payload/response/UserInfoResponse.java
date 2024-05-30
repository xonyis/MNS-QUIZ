package com.example.quizgame.payload.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    private String img;

    public UserInfoResponse(Long id, String username, String email, List<String> roles, String img) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.img = img;
    }

    public UserInfoResponse(Long id, String username, String email, Collection<? extends GrantedAuthority> authorities) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}