package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.mybatis.SqlSessionLoader;
import com.example.backend.service.UserService;
import com.example.backend.util.Response;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // login, register
    @Override
    public Response<User> login(String email, String password) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            User user = sqlSession.selectOne("UserMapper.login", email);
            if (user == null) {
                return new Response<>(1, "User not found", null);
            } else if (!user.getPassword().equals(password)) {
                return new Response<>(1, "Wrong password", null);
            } else {
                return new Response<>(0, "Login successfully", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Login failed", null);
        }
    }

    @Override
    public Response<User> register(String email, String password) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            User user = sqlSession.selectOne("UserMapper.login", email);
            if (user != null) {
                return new Response<>(1, "User already exists", null);
            } else {
                sqlSession.insert("UserMapper.register", new User(email, password));
                sqlSession.commit();
                return new Response<>(0, "Register successfully", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Register failed", null);
        }
    }

    @Override
    public Response<Integer> getSchedule(int id) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            Integer schedule = sqlSession.selectOne("UserMapper.getSchedule", id);
            if (schedule == null) {
                return new Response<>(1, "User not found", null);
            } else {
                return new Response<>(0, "Get schedule successfully", schedule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Get schedule failed", null);
        }
    }

    @Override
    public Response<User> updateSchedule(int id, int schedule) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            sqlSession.update("UserMapper.updateSchedule", new User(id, schedule));
            sqlSession.commit();
            return new Response<>(0, "Update schedule successfully", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Update schedule failed", null);
        }
    }
}
