package com.example.service;

import com.example.exception.UserNotFoundException;
import com.example.model.Archive;
import com.example.model.Task;
import com.example.model.User;

import java.util.List;

public interface ArchiveService {
    Archive addTask(String email,Task task);
    List<Task> showArchiveList(String email);
    String deleteTask(int taskId) throws UserNotFoundException;
    String unArchiveTask(int taskId) throws UserNotFoundException;

}

