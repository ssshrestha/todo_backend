package com.example.service;

import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;
import com.example.repo.TaskRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
//import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{


     @Autowired
    TaskService taskService;
     @Autowired
    TaskRepo taskRepo;


    @Override
    public User addTask(String email,Task task) {
        User user = taskRepo.findByEmail(email);
        if(user!=null){
            if(user.getTaskList()==null){
                user.setTaskList(Arrays.asList(task));
            }
            else {
                List<Task> taskList = user.getTaskList();
                taskList.add(task);
                user.setTaskList(taskList);
            }
            return taskRepo.save(user);
        }
        return user ;
    }

    @Override
    public User updateTask(String email, int taskId,Task task) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
         Task t =null;
        if(user==null){
            throw new UserNotFoundException();
        } else{
            for(Task task1:user.getTaskList()){
                if(task1.getTaskId()==taskId){
                    t=task1;
            System.out.println(taskId);
            System.out.println(task1);
                task1.setTaskTitle(task.getTaskTitle());
                task1.setTaskDescription(task.getTaskDescription());
                task1.setCategory(task.getCategory());
                task1.setDate(task.getDate());
                task1.setPriority(task.getPriority());
                }
            }
             if(t==null) {System.out.println("Task not found");}
            }
       return taskRepo.save(user);
    }

    @Override
    public User deleteTask(String email, int taskId) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
        boolean taskIdIsPresent=false;
        if(user==null){
            throw new UserNotFoundException();
        } else {
            List<Task> tasks = user.getTaskList();
            taskIdIsPresent = tasks.removeIf(x->x.getTaskId()==(taskId));
            if(!taskIdIsPresent)
            {
//                throw new TaskNotFoundException();
                System.out.println("Task not found");
            }
            user.setTaskList(tasks);
        }
        return taskRepo.save(user);
    }

    @Override
    public List<Task> showUserTask(String email) throws UserNotFoundException {

        User user = taskRepo.findByEmail(email);
        if(user==null){
            throw new UserNotFoundException();
        }
            return user.getTaskList();
    }





}
