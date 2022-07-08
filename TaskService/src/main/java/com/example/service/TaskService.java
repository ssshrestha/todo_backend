package com.example.service;

import com.example.exception.UserAlreadyExistException;
import com.example.model.User;

public interface TaskService {
    User registerUser(User user) throws UserAlreadyExistException;
    User updateUser(String email, User user);
}
