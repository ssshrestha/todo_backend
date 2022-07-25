
    package com.example.contoller;

import com.example.exception.UserNotFoundException;
import com.example.model.Task;
import com.example.repo.OrganizationRepo;
import com.example.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;

//    @CrossOrigin(origins="*")
    @RestController
    @RequestMapping("/api/v5")
    public class OrganizationServiceController {
        ResponseEntity responseEntity;
        @Autowired
        OrganizationService taskService;

        @Autowired
        OrganizationRepo taskRepo;

    @GetMapping("/showSortedTaskListByDates/{email}")
    public ResponseEntity showSortedTaskListByDates( @PathVariable ("email") String email) throws UserNotFoundException{
        return new ResponseEntity<>(taskService.organizeTaskListByDueDates(email),HttpStatus.CREATED);
    }

    @GetMapping("/searchTaskByDates/{email}/{date}")
    public ResponseEntity searchTaskByDates( @PathVariable ("email") String email,@PathVariable ("date") String date) throws UserNotFoundException, ParseException {
        return new ResponseEntity<>(taskService.searchTaskByDate(email,LocalDate.parse(date)),HttpStatus.CREATED);
    }

    @GetMapping("/todayTask/{email}")
    public ResponseEntity todayTask( @PathVariable ("email") String email) throws UserNotFoundException, ParseException {
        return new ResponseEntity<>(taskService.showTodayTask(email),HttpStatus.CREATED);
    }

    @GetMapping("/showTaskListByCategory/{email}/{category}")
    public ResponseEntity showTaskListByCategory( @PathVariable ("email") String email,@PathVariable ("category") String category) throws UserNotFoundException{
        return new ResponseEntity<>(taskService.searchTaskByCategory(email,category),HttpStatus.CREATED);
    }

    @GetMapping("/sortByPriority/{email}/{order}")
    public ResponseEntity sortTaskByPriority( @PathVariable ("email") String email,@PathVariable ("order") String order) throws UserNotFoundException, ParseException {
        return new ResponseEntity<>(taskService.sortTaskListByPriority(email,order),HttpStatus.CREATED);
    }


}


