package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.util.JwtUtil;
import com.example.backend.util.Response;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response<User>> login(@RequestParam(value = "email") String email,
                                                @RequestParam(value = "password") String password) {
        Response<User> result = userService.login(email, password);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Response<User>> register(@RequestParam(value = "email") String email,
                                                   @RequestParam(value = "password") String password) {
        return ResponseEntity.ok(userService.register(email, password));
    }

    @GetMapping("/getSchedule")
    public ResponseEntity<Response<Integer>> getSchedule(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.getSchedule(email));
    }

    @PostMapping("/updateSchedule")
    public ResponseEntity<Response<User>> updateSchedule(@RequestParam(value = "schedule") int schedule, @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.updateSchedule(email, schedule));
    }

    @GetMapping("/statOfSchedule")
    public ResponseEntity<Response<List<Integer>>> statOfSchedule() {
        return ResponseEntity.ok(userService.statOfSchedule());
    }

    @GetMapping("/getStage")
    public ResponseEntity<Response<Integer>> getStage(@RequestParam(value = "username") String email) {
        return ResponseEntity.ok(userService.getStage(email));
    }

    @PostMapping("/updateStage")
    public ResponseEntity<Response<Integer>> updateStage(@RequestParam(value = "stage") int stage, @RequestParam(value = "username") String email) {
        return ResponseEntity.ok(userService.updateStage(email, stage));
    }

}
