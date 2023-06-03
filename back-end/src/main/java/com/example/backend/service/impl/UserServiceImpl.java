package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import com.example.backend.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    @Override
    public Response<User> login(String email, String password) {
        User user=userMapper.findUserByEmail(email);
        if (user==null){
            return new Response<>(0,"用户未注册",null);
        }
        user=userMapper.findUserByEmailAndPassword(email,password);
        if (user==null){
            return new Response<>(0,"密码错误！",null);
        }
        else {
            return new Response<>(1,"success",user);
        }
    }

    @Override
    public Response<User> register(String email, String password) {
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        if (userMapper.findUserByEmail(email)!=null){
            return new Response<>(0,"用户已经存在",null);
        }
        else {
            userMapper.save(user);
            return new Response<>(1,"success",user);
        }
    }
}
