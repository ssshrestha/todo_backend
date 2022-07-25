package com.example.Register.controllerTest;

import com.example.contoller.TaskController;
import com.example.model.Task;
import com.example.model.User;
import com.example.service.TaskServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerLayerTest {

    private MockMvc mockMvc;
    @Mock
    private TaskServiceImpl taskService;
    @InjectMocks
    private TaskController taskController;

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
        mockMvc= MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @AfterEach
    public void afterTestMethod(){
        user=null;
    }

    public static String convertJsonToString(Object obj){
        String result=null;
        try{
            ObjectMapper mapper=new ObjectMapper();
            String jsonContent=mapper.writeValueAsString(obj);
            result=jsonContent;
        }catch(JsonProcessingException jsonexce){
            System.out.println("Error in JSON Processing:"+jsonexce);
        }
        return result;
    }

    @Test
    public void registerTest() throws Exception {
        when(taskService.addTask(any(),any())).thenReturn(user);
        mockMvc.perform(post("/api/v3/addTask/ssshrestha18@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertJsonToString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void showTask() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/showTask/ssshrestha18@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteTask() throws Exception{
        when(taskService.deleteTask(user.getEmail(),task.getTaskId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v3/delete/ssshrestha18@gmail.com/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTask() throws Exception{
        when(taskService.deleteTask(user.getEmail(),task.getTaskId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v3/delete/ssshrestha18@gmail.com/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void updateTask() throws Exception{
//
//        when(taskService.updateTask(user.getEmail(),task.getTaskId(),task)).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v3/update/ssshrestha@gmail.com/1")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(convertJsonToString(task)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//    }
}
