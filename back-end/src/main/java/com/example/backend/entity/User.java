package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String email;
    private String password;
    private Integer schedule;
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, int schedule) {
        this.userId = id;
        this.schedule = schedule;
    }
}
