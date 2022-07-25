package com.example.service;

import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;

public interface RegisterService {
    User registerUser(User user) throws UserAlreadyExistException;
    User registerUser1(User user) throws UserAlreadyExistException;
    User getUser(String email) throws UserAlreadyExistException;
    User updateUser(String email, User user);
    User setImage(String email,String image);
    User addTask(String email, Task task);
}
