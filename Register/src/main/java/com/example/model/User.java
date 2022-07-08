package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    @Id
//    private int userId;
    private String email;
    private String username;
    private String userImage;
    private String mobileNo;
    @Transient
    private String password;
    private List<Task> taskList;

}


