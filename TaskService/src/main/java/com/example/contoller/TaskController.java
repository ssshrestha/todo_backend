
    package com.example.contoller;

import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.model.User;
import com.example.repo.TaskRepo;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @CrossOrigin(origins="*")
    @RestController
    @RequestMapping("/api/v3")
    public class TaskController {
        ResponseEntity responseEntity;
        @Autowired
        TaskService taskService;

        @Autowired
        TaskRepo taskRepo;

        @GetMapping("/")
        public String home() {
            return "Reached TestMovieController";
        }

        @PostMapping("/addTask/{email}")
        public ResponseEntity addTask(@RequestBody Task task, @PathVariable ("email") String email){
            return new ResponseEntity<>(taskService.addTask(email,task),HttpStatus.CREATED);
        }

        @GetMapping("/showTask/{email}")
        public ResponseEntity showTask( @PathVariable ("email") String email) throws UserNotFoundException{
            return new ResponseEntity<>(taskService.showUserTask(email),HttpStatus.CREATED);
        }

        @DeleteMapping("/delete/{email}/{taskId}")
        public ResponseEntity deleteTask(@RequestBody Task task, @PathVariable ("email") String email,@PathVariable ("taskId") int taskId) throws UserNotFoundException {
            return new ResponseEntity<>(taskService.deleteTask(email,taskId),HttpStatus.OK);
        }

        @PutMapping("/update/{email}/{taskId}")
        public ResponseEntity updateTask(@RequestBody Task task, @PathVariable ("email") String email,@PathVariable ("taskId") int taskId) throws UserNotFoundException {
            return new ResponseEntity<>(taskService.updateTask(email,taskId,task),HttpStatus.OK);
        }

    }


