package com.example.backend.service.impl;

import com.example.backend.entity.User;
import com.example.backend.mybatis.SqlSessionLoader;
import com.example.backend.service.UserService;
import com.example.backend.util.Response;
import org.springframework.stereotype.Service;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // login, register
    @Override
    public Response<User> login(String email, String password) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            User user = sqlSession.selectOne("findUserByEmail", email);
            if (user == null) {
                return new Response<>(1, "用户未注册", null);
            } else if (!user.getPassword().equals(password)) {
                return new Response<>(1, "密码错误！", null);
            } else {
                return new Response<>(0, "success", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Login failed", null);
        }
    }

    @Override
    public Response<User> register(String email, String password) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            User user = sqlSession.selectOne("findUserByEmail", email);
            if (user != null) {
                return new Response<>(1, "用户已经存在", null);
            } else {
                sqlSession.insert("createUser", new User(email, password));
                sqlSession.commit();
                return new Response<>(0, "Register successfully", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Register failed", null);
        }
    }

    @Override
    public Response<Integer> getSchedule(String email) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            User user = sqlSession.selectOne("findUserByEmail", email);
            if (user == null) {
                return new Response<>(1, "User not found", null);
            }
            Integer schedule = user.getSchedule();
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
    public Response<Integer> updateSchedule(String email, int schedule) {
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            User user = sqlSession.selectOne("findUserByEmail", email);
            if (user == null) {
                return new Response<>(1, "User not found", null);
            }
            if (schedule > user.getSchedule()) {
                user.setSchedule(schedule);
                sqlSession.update("updateUser", user);
                sqlSession.commit();
                return new Response<>(0, "Update schedule successfully", null);
            } else {
                return new Response<>(0, "Not Update schedule", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Update schedule failed", null);
        }
    }
    @Override
    public Response<List<Integer>> statOfSchedule() { // count each schedule(1,2,3,4,5,6)
        try (SqlSession sqlSession = SqlSessionLoader.getSqlSession()) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                Integer count = sqlSession.selectOne("countSchedule", i);
                if (count == null) {
                    return new Response<>(1, "Get stat failed", null);
                }
                list.add(count);
            }
            return new Response<>(0, "Get stat successfully", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(1, "Get stat failed", null);
        }
    }
}
