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
    User addTaskArchive(String email, Task task);
    User deleteImage(String email, int taskId);
    User deleteCategory(String email, int taskId);
    User updateTask(String email, int taskId, Task task) throws UserNotFoundException;
    User deleteTask(String email,int taskId) throws UserNotFoundException;
    User setTaskImage(String email,int taskId,String image) throws UserNotFoundException;
    User setTaskCategory(String email,int taskId,String category) throws UserNotFoundException;
    User setTaskPriority(String email,int taskId,String priority) throws UserNotFoundException;
    User moveTaskToArchive(String email,int taskId) throws UserNotFoundException;
    User moveTaskCompletedToArchive(String email) throws UserNotFoundException;
    List<Task> showUserTask(String email) throws UserNotFoundException;

    User setTaskCardColor(String email , int taskId ,String taskCardColor) throws UserNotFoundException;

}
