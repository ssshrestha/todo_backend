package com.example.service;

import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;

import java.util.List;

public interface TaskService {
//    User registerUser(User user) throws UserAlreadyExistException;
//    User updateUser(String email, User user);
    User addTask(String email, Task task);
    User updateTask(String email,int taskId,Task task) throws UserNotFoundException;
    User deleteTask(String email,int taskId) throws UserNotFoundException;
    List<Task> showUserTask(String email) throws UserNotFoundException;
}
