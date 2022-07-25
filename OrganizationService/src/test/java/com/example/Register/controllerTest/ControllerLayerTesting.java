package com.example.Register.controllerTest;

import com.example.contoller.OrganizationServiceController;
import com.example.model.Task;
import com.example.model.User;
import com.example.service.OrganizationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ControllerLayerTesting {
    private MockMvc mockMvc;
    @Mock
    private OrganizationServiceImpl organizationService;
    @InjectMocks
    private OrganizationServiceController organizationServiceController;

    User user;
    Task task;
    Task task1;

    @BeforeEach
    public void setup(){
        task=new Task(1,"T1","ABC","","work", LocalDate.now(),"low","red");
        task1=new Task(2,"T1","ABC","","work",LocalDate.now(),"low","red");
        List<Task> newTaskList=new ArrayList();
        newTaskList.add(task);
        newTaskList.add(task1);
        user=new User("ssshrestha18@gmail.com","shubham","","8683910755","123456789", newTaskList);
        mockMvc= MockMvcBuilders.standaloneSetup(organizationServiceController).build();
    }

    @AfterEach
    public void afterTestMethod(){
        user=null;
    }

    @Test
    public void showSortedTaskListByDates() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v5/showSortedTaskListByDates/ssshrestha18@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void searchTaskByDates() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v5/searchTaskByDates/ssshrestha18@gmail.com/2022-01-01"))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void todayTask() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v5/todayTask/ssshrestha18@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void sortTaskByPriority() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v5/sortByPriority/ssshrestha18@gmail.com/low"))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void showTaskListByCategory() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v5/showTaskListByCategory/ssshrestha18@gmail.com/bill"))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
    }


}
