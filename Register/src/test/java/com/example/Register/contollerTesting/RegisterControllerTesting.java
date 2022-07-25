package com.example.Register.contollerTesting;

import com.example.contoller.RegisterController;
import com.example.model.Task;
import com.example.model.User;
import com.example.service.RegisterServiceImpl;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTesting {
    private MockMvc mockMvc;
    @Mock
    private RegisterServiceImpl registerService;
    @InjectMocks
    private RegisterController registerController;

    User user;
    Task task;
    Task task1;


    @BeforeEach
    public void setup(){
        task=new Task(1,"T1","ABC","","work",new Date(),"low","red");
        task1=new Task(2,"T1","ABC","","work",new Date(),"low","red");
        List<Task> newTaskList=new ArrayList();
        newTaskList.add(task);
        newTaskList.add(task1);
        user=new User("ssshrestha18@gmail.com","shubham","","8683910755","123456789", newTaskList);
        mockMvc= MockMvcBuilders.standaloneSetup(registerController).build();
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
        when(registerService.registerUser((User) any())).thenReturn(user);
        mockMvc.perform(post("/api/v2/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertJsonToString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

}
