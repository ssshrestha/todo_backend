package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Task {
    @Id
    int taskId;
    String taskTitle;
    String taskDescription;
    String category;
    Date date;
    String priority;
}
