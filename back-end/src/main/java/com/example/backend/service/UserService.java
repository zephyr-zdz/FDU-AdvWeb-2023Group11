package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.util.Response;

import java.util.List;

public interface UserService {
    Response<User> login(String email, String password);
    Response<User> register(String email,String password);
    Response<Integer> getSchedule(String email);
    Response<Integer> updateSchedule(String email, int schedule);
    Response<List<Integer>> statOfSchedule();
}
