package com.example.service;

import com.example.exception.UserAlreadyExistException;
import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;
import com.example.proxy.UserProxy;
import com.example.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class RegisterServiceImpl implements RegisterService{


     @Autowired
     RegisterService registerService;
     @Autowired
     RegisterRepo registerRepo;
     @Autowired
     UserProxy userProxy;


        @Override
        public User registerUser(User user)   throws UserAlreadyExistException {
            System.out.println("In user service impl register user function");
            User user1 = registerRepo.findByEmail(user.getEmail());

            if(user1!=null)
            {System.out.println("user already exist");
                throw new UserAlreadyExistException();}
            List<User> userList = registerRepo.findAll();
            System.out.println("before reg in sql");
            ResponseEntity r = userProxy.registerUser(user);
//            System.out.println("after reg in sql");
//            System.out.println(r);
//            System.out.println(r.getBody());
            return registerRepo.save(user);}

    @Override
    public User registerUser1(User user)   throws UserAlreadyExistException {
        System.out.println("In user service impl register user function");
        User user1 = registerRepo.findByEmail(user.getEmail());

        if(user1!=null)
        {System.out.println("user already exist");
            throw new UserAlreadyExistException();}
        List<User> userList = registerRepo.findAll();
        System.out.println("before reg in sql");
//        ResponseEntity r = userProxy.registerUser(user);
//            System.out.println("after reg in sql");
//            System.out.println(r);
//            System.out.println(r.getBody());
        return registerRepo.save(user);}

    @Override
    public User getUser(String email) throws UserAlreadyExistException {
        User user = registerRepo.findByEmail(email);
        return user;
    }


    @Override
        public User updateUser(String email,User user){
            User user1 = registerRepo.findByEmail(email);
            List<User> userList = registerRepo.findAll();
            User user2 = userList.get(userList.size()-1);
            System.out.println("last user is : "+user2);
            user1.setUsername(user.getUsername());
            user1.setUserImage(user.getUserImage());
            user1.setMobileNo(user.getMobileNo());
            return registerRepo.save(user1);
        }

    @Override
    public User setImage(String email,String image) {
        User user1 = registerRepo.findByEmail(email);
        user1.setUserImage(image);
        return registerRepo.save(user1);
    }

    @Override
    public User addTask(String email, Task task) {
            User user = registerRepo.findByEmail(email);
            if(user!=null){
                if(user.getTaskList()==null){
                    user.setTaskList(Arrays.asList(task));
                }
                else {
                    List<Task> taskList = user.getTaskList();
                    taskList.add(task);
                    user.setTaskList(taskList);
                }
                return registerRepo.save(user);
            }
            return user ;
    }


}
