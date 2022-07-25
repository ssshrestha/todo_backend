package com.example.controller;

import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserRepository;
import com.example.service.JWTSecurityTokenGen;
import com.example.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTSecurityTokenGen jwtSecurityTokenGen;


    @PostMapping("/login")
//    @HystrixCommand(fallbackMethod = "fallbackLogin")
//    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    public ResponseEntity<?> credentialCheck(@RequestBody User user)throws UserNotFoundException{
        ResponseEntity responseEntity;
        try
        {
//            Thread.sleep(100);
            User userObj=userService.findByEmailAndPasswordCheck(user.getEmail(),user.getPassword());

            if(userObj.getEmail().equals(user.getEmail()))
            {
                //Creating a Token
                Map<String,String> tokenMap=jwtSecurityTokenGen.generateToken(userObj);
                responseEntity=new ResponseEntity<>(tokenMap,HttpStatus.OK);
            }
            else{
                responseEntity=new ResponseEntity<>("InValid User",HttpStatus.NOT_FOUND);
            }
        }
        catch(UserNotFoundException ue){
            throw ue;
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity<>("Some Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

//    public ResponseEntity<?> fallbackLogin(@RequestBody User user)
//        throws InvalidCredentialsException{
//        String msg = "login failed";
//        return  new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        User newUser=userService.registerUser(user);
        return new ResponseEntity<>("User Created",HttpStatus.CREATED);
    }

    @PutMapping("/updatePassword/{email}/{password}/{pass}")
    public ResponseEntity<?> updatePasswordByEmailCheck(@RequestBody(required = false) @PathVariable ("password") String password,@PathVariable ("pass") String password2,@PathVariable ("email") String email) throws UserNotFoundException{
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<>(userService.updatePasswordByEmailCheck(email,password,password2), HttpStatus.OK);
//        userService.updatePasswordByEmailCheck(email,password,password2);
        return  responseEntity;
    }


    @PostMapping("/cancel/{id}")
    public List<User> cancelRegistration(@PathVariable("id") int userid) {
        userRepository.deleteById(userid);
        return userRepository.findAll();
    }

    @GetMapping("/checkEmailAvailablity/{email}")
   public boolean EmailNotAvailablityCheck(@PathVariable ("email") String email){
        return userService.EmailNotAvailablityCheck(email);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
