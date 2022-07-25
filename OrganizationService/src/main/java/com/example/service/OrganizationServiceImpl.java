package com.example.service;

import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;
import com.example.repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrganizationServiceImpl implements OrganizationService {

     @Autowired
     OrganizationService organizationService;
     @Autowired
     OrganizationRepo organizationRepo;

    @Override
    public  List<Task>  organizeTaskListByDueDates(String email) {
        User user = organizationRepo.findByEmail(email);
        List<Task> taskList = user.getTaskList();
        taskList.sort((d1,d2)->d1.getDate().compareTo(d2.getDate()));
        return taskList;
    }


    @Override
    public List<Task> searchTaskByCategory(String email, String category) {
        User user = organizationRepo.findByEmail(email);
        List<Task> taskList = user.getTaskList();
        List<Task> taskListByCategory = new ArrayList<>();
        for (Task task:taskList) {
            if(task.getCategory().equalsIgnoreCase((category)) || task.getTaskTitle().equalsIgnoreCase((category))
                    || task.getPriority().equalsIgnoreCase((category))  || task.getTaskDescription().equalsIgnoreCase((category))){
                taskListByCategory.add(task);
            }


        }
        return taskListByCategory;
    }

    @Override
    public List<Task> searchTaskByDate(String email, LocalDate date) {
        User user = organizationRepo.findByEmail(email);
        List<Task> taskList = user.getTaskList();
        List<Task> taskListByDate = new ArrayList<>();
        for (Task task:taskList) {
            if(task.getDate().equals((date))){
                taskListByDate.add(task);
            }
        }
        return taskListByDate;
    }

    @Override
    public List<Task> showTodayTask(String email) {
        User user = organizationRepo.findByEmail(email);
        List<Task> taskList = user.getTaskList();
        List<Task> taskListByDate = new ArrayList<>();
        LocalDate date1 = LocalDate.now();
        System.out.println("today date : "+date1);
        for (Task task:taskList) {
            if(date1.equals(task.getDate())){
                taskListByDate.add(task);
            }}
        return taskListByDate;
    }

    @Override
    public List<Task> sortTaskListByPriority(String email,String order) {
        User user = organizationRepo.findByEmail(email);
        List<Task> taskList = user.getTaskList();
        List<Task> lowList =new ArrayList<>();
        List<Task> mediumList =new ArrayList<>();
        List<Task> highList =new ArrayList<>();
        List<Task> priorityList = new ArrayList<>();
        for(Task t : taskList){
            if(t.getPriority().equalsIgnoreCase("low")){
                lowList.add(t);
            } else if(t.getPriority().equalsIgnoreCase("medium")){
                mediumList.add(t);
            } else if(t.getPriority().equalsIgnoreCase("high")){
                highList.add(t);
            }}
        if(order.equalsIgnoreCase("a")) {
            priorityList.addAll(lowList);
            priorityList.addAll(mediumList);
            priorityList.addAll(highList);
        }
        else if(order.equalsIgnoreCase("d")){
            priorityList.addAll(highList);
            priorityList.addAll(mediumList);
            priorityList.addAll(lowList);
        }
        return priorityList;
    }



}
