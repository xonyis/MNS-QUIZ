package com.example.quizgame.dto;

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String img;

    // Constructors
    public UserDto(Long id, String username, String email, String role, String img) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.img = img;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}