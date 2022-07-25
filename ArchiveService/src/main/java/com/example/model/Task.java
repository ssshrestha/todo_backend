package com.example.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
    String taskImage;
    LocalDate date;
    String priority;
    String taskCardColor;
}
