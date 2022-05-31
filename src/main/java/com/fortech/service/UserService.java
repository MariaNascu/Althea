package com.fortech.service;

import com.fortech.dtos.UserResponse;
import com.fortech.entity.User;

import java.util.List;

public interface UserService {

    UserResponse findById(Integer userId);

    User findByUserName(String username);

    User findByEmail(String email);

    User findByPhone(String phone);

    User createUser(User user);

    void validateUser(User user);

    void deleteUser(Integer userId);

    User updateUser(Integer userId, User user);

    void promote(Integer userId);

    List<User> findAllUsers();
}
