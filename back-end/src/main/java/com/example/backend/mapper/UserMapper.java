package com.example.backend.mapper;

import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findUserByEmail(@Param("email") String email);
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    User findUserByUserId(@Param("userId") int userId);
    User createUser(@Param("email") String email, @Param("password") String password);
    User updateUser(@Param("userId") int userId, @Param("schedule") int schedule);
    Integer countSchedule(@Param("schedule") int schedule);
}
