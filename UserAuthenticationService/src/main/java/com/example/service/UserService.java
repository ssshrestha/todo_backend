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

        public User updatePasswordByEmailCheck(String email,String password)throws UserNotFoundException
    {User user=userRepository.findByEmail(email);
        if(user==null){
            throw new UserNotFoundException();
        } else {user.setPassword(password);
        }return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
