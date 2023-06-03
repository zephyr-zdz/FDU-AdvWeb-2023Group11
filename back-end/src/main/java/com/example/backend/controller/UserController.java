package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.util.JwtUtil;
import com.example.backend.util.Response;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }

    private int getIdFromToken(HttpServletRequest request){
        Claims claims=JwtUtil.parse(request.getHeader("Authorization"));
        if (claims==null) return -1;
        return (int) claims.get("id");
    }
    @PostMapping("/login")
    public ResponseEntity<Response<User>> login(@RequestParam(value = "email") String email,
                                                @RequestParam(value = "password") String password,
                                                HttpServletResponse response){
        Response<User> result=userService.login(email, password);
        if (result.getCode()==1){
            String accessToken = JwtUtil.createJWT(result.getData().getUserId());
            response.setHeader("Authorization",accessToken);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/register")
    public ResponseEntity<Response<User>> register(@RequestParam(value = "email") String email,
                                                   @RequestParam(value = "password") String password){
        return ResponseEntity.ok(userService.register(email, password));
    }
    @GetMapping("/getSchedule")
    public ResponseEntity<Response<Integer>> getSchedule(HttpServletRequest request){
        int id=getIdFromToken(request);
        return ResponseEntity.ok(userService.getSchedule(id));
    }
    @PostMapping("/updateSchedule")
    public ResponseEntity<Response<User>> updateSchedule(@RequestParam(value = "schedule") int schedule,
                                                         HttpServletRequest request){
        int id=getIdFromToken(request);
        return ResponseEntity.ok(userService.updateSchedule(id,schedule));
    }
}
