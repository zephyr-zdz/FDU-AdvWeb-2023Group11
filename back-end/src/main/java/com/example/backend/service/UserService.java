package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.util.Response;

public interface UserService {
    Response<User> login(String email, String password);
    Response<User> register(String email,String password);
}
