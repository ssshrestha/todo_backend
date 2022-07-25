
    package com.example.contoller;

import com.example.exception.UserAlreadyExistException;
import com.example.model.Task;
import com.example.model.User;
import com.example.repo.RegisterRepo;
import com.example.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/v2")
    public class RegisterController {
        ResponseEntity responseEntity;
        @Autowired
        RegisterService registerService;

        @Autowired
        RegisterRepo registerRepo;

        @GetMapping("/")
        public String home() {
            return "Reached TestMovieController";
        }



        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody User user) {
            System.out.println(" in register function");
            try {
                System.out.println(" in register function");

                responseEntity = new ResponseEntity<>(registerService.registerUser(user), HttpStatus.CREATED);
                System.out.println(" in after register function");
            } catch (Exception e) {
                responseEntity = new ResponseEntity<>("Not Registered", HttpStatus.INTERNAL_SERVER_ERROR);

                System.out.println(e);
            }
            return responseEntity;
        }

        @PostMapping("/update/{email}")
        public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable ("email") String email) {
            System.out.println(" in update function");
            try {
                System.out.println(" in register function");

                responseEntity = new ResponseEntity<>(registerService.updateUser(email,user), HttpStatus.CREATED);
                System.out.println(" in after register function");
            } catch (Exception e) {
                responseEntity = new ResponseEntity<>("Not Registered", HttpStatus.INTERNAL_SERVER_ERROR);

                System.out.println(e);
            }
            return responseEntity;
        }

    @PostMapping("/setUserImage/{email}")
    public ResponseEntity<?> setUserImage(@RequestBody String image,@PathVariable ("email") String email) {
      return responseEntity = new ResponseEntity<>(registerService.setImage(email,image), HttpStatus.OK);
    }

        @PostMapping("/addTask/{email}")
        public ResponseEntity addTask(@RequestBody Task task, @PathVariable ("email") String email){
            return new ResponseEntity<>(registerService.addTask(email,task),HttpStatus.CREATED);
        }

        @GetMapping("/findUser/{email}")
        public ResponseEntity findUser( @PathVariable ("email") String email) throws UserAlreadyExistException {
            return new ResponseEntity<>(registerService.getUser(email),HttpStatus.CREATED);
        }

    }


