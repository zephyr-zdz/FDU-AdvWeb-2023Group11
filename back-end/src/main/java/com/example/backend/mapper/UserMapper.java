package com.example.backend.mapper;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
    User findUserByUserId(int id);
}
