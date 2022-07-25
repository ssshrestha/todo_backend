package com.example.Register.repoTest;

import com.example.model.Task;
import com.example.model.User;
import com.example.repo.RegisterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RegisterRepoTest {
    @Autowired
    private RegisterRepo registerRepo;

    private User user;
    private Task task;
    Task task1;

    @BeforeEach
    public void setup(){
        task=new Task(1,"T1","ABC","","work",new Date(),"low","red");
        task1=new Task(2,"T1","ABC","","work",new Date(),"low","red");
        List<Task> newTaskList=new ArrayList();
        newTaskList.add(task);
        newTaskList.add(task1);
        user=new User("ssshrestha18@gmail.com","shubham","","8683910755","123456789", newTaskList);
    }

    @Test
    public void registerUser(){
        registerRepo.save(user);
        User user1=registerRepo.findByEmail(user.getEmail());
        assertNotNull(user1);
        assertEquals(user.getEmail(),user1.getEmail());
    }
}
