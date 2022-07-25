package com.example.service;

import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){

        return userRepository.save(user);
    }

    public User findByEmailAndPasswordCheck(String email,String password)throws UserNotFoundException
    {User user=userRepository.findByEmailAndPassword(email,password);
        if(user==null){
            throw new UserNotFoundException();
        }return user;
    }

    public boolean EmailNotAvailablityCheck(String email){
            boolean avaiablability=false;
    User user=userRepository.findByEmail(email);
        if(user==null){
            avaiablability=true;
        }
        return avaiablability;
        }

    public boolean updatePasswordByEmailCheck(String email,String pass,String password)throws UserNotFoundException
    {User user=userRepository.findByEmail(email);
        boolean status=false;
        if(user==null){
            throw new UserNotFoundException();
        } else {
            System.out.println("inside else");
            if(user.getPassword().equalsIgnoreCase(pass)) {
                user.setPassword(password);
                status=true;
            }
        } userRepository.save(user);
        System.out.println("status is : "+status);
        return status;
    }



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
