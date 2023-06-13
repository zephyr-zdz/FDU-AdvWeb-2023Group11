package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.util.Response;
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
    public ResponseEntity<Response<User>> register(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        return ResponseEntity.ok(userService.register(email, password));
    }

    @GetMapping("/getSchedule")
    public ResponseEntity<Response<Integer>> getSchedule(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.getSchedule(email));
    }

    @PostMapping("/updateSchedule")
    public ResponseEntity<Response<Integer>> updateSchedule(@RequestParam(value = "schedule") int schedule, @RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.updateSchedule(email, schedule));
    }

    @GetMapping("/statOfSchedule")
    public ResponseEntity<Response<List<Integer>>> statOfSchedule() {
        return ResponseEntity.ok(userService.statOfSchedule());
    }

}
