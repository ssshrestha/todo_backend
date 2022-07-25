
package com.example.contoller;

import com.example.exception.UserNotFoundException;
import com.example.model.Archive;
import com.example.model.Task;
import com.example.repo.ArchiveRepo;
import com.example.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/v4")
    public class ArchiveController {
        ResponseEntity responseEntity;
        @Autowired
        ArchiveService archiveService;

        @Autowired
        ArchiveRepo archiveRepo;

        @GetMapping("/")
        public String home() {
            return "Reached TestMovieController";
        }

        @PostMapping("/addTask/{email}")
        public ResponseEntity addTask(@RequestBody Task task,@PathVariable ("email") String email){
            return new ResponseEntity<>(archiveService.addTask(email,task),HttpStatus.CREATED);
        }

        @GetMapping("/showArchive/{email}")
        public ResponseEntity showTask(@PathVariable ("email") String email) {
            return new ResponseEntity<>(archiveService.showArchiveList(email),HttpStatus.CREATED);
        }

        @DeleteMapping("/delete/{archiveId}")
        public ResponseEntity deleteTask(@PathVariable ("archiveId") int archiveId) throws UserNotFoundException {
            return new ResponseEntity<>(archiveService.deleteTask(archiveId),HttpStatus.OK);
        }

        @PostMapping("/unArchive/{archiveId}")
        public ResponseEntity unArchiveTask(@PathVariable ("archiveId") int archiveId) throws UserNotFoundException {
            return new ResponseEntity<>(archiveService.unArchiveTask(archiveId),HttpStatus.OK);
        }


    }


