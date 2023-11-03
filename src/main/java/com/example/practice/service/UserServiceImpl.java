package com.example.practice.service;

import com.example.practice.common.response.RestAPIStatus;
import com.example.practice.common.response.RestApiMessage;
import com.example.practice.common.validator.Validator;
import com.example.practice.entities.User;
import com.example.practice.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositories userRepositories;

    @Override
    public User getUserById(String id) {
        User user = userRepositories.findById(id).orElse(null);
        Validator.notNull(user, RestAPIStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        return user;
    }
}
