package com.example.service;

import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;
import com.example.proxy.ArchiveProxy1;
import com.example.repo.TaskRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
                if(taskList.isEmpty()){
                    task.setTaskId(1);
                }
                else {
                    Task task1 = taskList.get(taskList.size()-1);
                    System.out.println("last task is : " + task1);
                    task.setTaskId(task1.getTaskId() + 1);
                }
                LocalDate date =LocalDate.now();
                if(!task.getDate().equals(date)) {
                    task.setDate(task.getDate().plusDays(1));
                }
                taskList.add(task);
                user.setTaskList(taskList);
            }
            return taskRepo.save(user);
        }
        return user ;
    }

    @Override
    public User addTaskArchive(String email, Task task) {
        User user = taskRepo.findByEmail(email);
        if(user!=null){
            if(user.getTaskList()==null){
                user.setTaskList(Arrays.asList(task));
            } else {
                List<Task> taskList = user.getTaskList();
                taskList.add(task);
                user.setTaskList(taskList);
            }return taskRepo.save(user);
        }return user ;
    }

    @Override
    public User deleteImage(String email, int taskId) {
        User user = taskRepo.findByEmail(email);
        for(Task task1:user.getTaskList()){
            if(task1.getTaskId()==taskId){
                task1.setTaskImage("");
            }}
        return taskRepo.save(user);
    }

    @Override
    public User deleteCategory(String email, int taskId) {
        User user = taskRepo.findByEmail(email);
        for(Task task1:user.getTaskList()){
            if(task1.getTaskId()==taskId){
                task1.setCategory("");
            }}
        return taskRepo.save(user);
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
                    LocalDate date =LocalDate.now();
                    if(!task.getDate().equals(task1.getDate())) {
                        task1.setDate(task.getDate().plusDays(1));
                    }

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
                System.out.println("Task not found");
            }

            user.setTaskList(tasks);
        }
        return taskRepo.save(user);
    }

    @Override
    public User setTaskImage(String email, int taskId,String image) throws UserNotFoundException {
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
                    task1.setTaskImage(image);

                }
            }
            if(t==null) {System.out.println("Task not found");}
        }
        return taskRepo.save(user);
    }

    @Override
    public User setTaskCategory(String email, int taskId, String category) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
        Task t =null;
        if(user==null){
            throw new UserNotFoundException();
        } else{for(Task task1:user.getTaskList()){
            if(task1.getTaskId()==taskId){
                t=task1;
                System.out.println(taskId);
                System.out.println(task1);
                task1.setCategory(category);
            }}if(t==null) {System.out.println("Task not found");}
        }return taskRepo.save(user);
    }

    @Override
    public User setTaskPriority(String email, int taskId, String priority) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
        Task t =null;
        if(user==null){
            throw new UserNotFoundException();
        } else{for(Task task1:user.getTaskList()){
            if(task1.getTaskId()==taskId){
                t=task1;
                System.out.println(taskId);
                System.out.println(task1);
                task1.setPriority(priority);
            }}if(t==null) {System.out.println("Task not found");}
        }return taskRepo.save(user);
    }

    @Autowired
    ArchiveProxy1 archiveProxy;

    @Override
    public User moveTaskToArchive(String email, int taskId) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
        boolean taskIdIsPresent=false;
        if(user==null){
            throw new UserNotFoundException();
        } else {
            List<Task> tasks = user.getTaskList();
            for(Task task:user.getTaskList()){
                System.out.println("tasklist is : "+task);
                System.out.println("task.getTasId : "+task.getTaskId());
                System.out.println("taskId : "+taskId);
                if(task.getTaskId()==taskId){

                    ResponseEntity r = archiveProxy.addTask(task,email);
                    System.out.println("value of r is : "+r);
                    System.out.println("body of r is : "+r.getBody());
                }
            }
            taskIdIsPresent = tasks.removeIf(x->x.getTaskId()==(taskId));
            if(!taskIdIsPresent)
            {
                System.out.println("Task not found");
            }
            user.setTaskList(tasks);
        }
        return taskRepo.save(user);
    }

    @Override
    public User moveTaskCompletedToArchive(String email) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
        boolean taskIdIsPresent=false;
        if(user==null){
            throw new UserNotFoundException();
        } else {
            List<Task> tasks = user.getTaskList();
            LocalDate date = LocalDate.now();
//            System.out.println("today date is : "+date);
            for(Task task:user.getTaskList()){
//                System.out.println("task date is : "+task.getDate());

                if(task.getDate().isBefore(date)){
                    ResponseEntity r = archiveProxy.addTask(task,email);
                    System.out.println("value of r is : "+r);
                    System.out.println("body of r is : "+r.getBody());
                }
            }
            taskIdIsPresent = tasks.removeIf(x->x.getDate().isBefore(date));
            if(!taskIdIsPresent)
            {
//                throw new TaskNotFoundException();
//                System.out.println("Task not found");
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

    @Override
    public User setTaskCardColor(String email, int taskId, String taskCardColor) throws UserNotFoundException {
        User user = taskRepo.findByEmail(email);
        Task t =null;
        if(user==null){
            throw new UserNotFoundException();
        } else{for(Task task1:user.getTaskList()){
            if(task1.getTaskId()==taskId){
                t=task1;
                System.out.println(taskId);
                System.out.println(task1);
                task1.setTaskCardColor(taskCardColor);
            }}if(t==null) {System.out.println("Task not found");}
        }return taskRepo.save(user);

    }

}
