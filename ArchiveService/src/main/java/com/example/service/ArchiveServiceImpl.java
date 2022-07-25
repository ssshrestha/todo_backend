package com.example.service;

import com.example.exception.UserNotFoundException;
import com.example.model.Archive;
import com.example.model.Task;
import com.example.model.User;
import com.example.proxy.ArchiveProxy;
import com.example.repo.ArchiveRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService{


    @Autowired
    ArchiveService archiveService;
    @Autowired
    ArchiveRepo archiveRepo;

    @Autowired
    ArchiveProxy archiveProxy;


    @Override
    public Archive addTask(String email,Task task) {
        Archive archive =new Archive();
        List<Archive> archiveList = archiveRepo.findAll();
        if(archiveList.size()==0){
            archive.setArchiveId(1);
        }
        else {
            Archive archive1 = archiveList.get(archiveList.size() - 1);
            System.out.println("last archive is : " + archive1);
            archive.setArchiveId(archive1.getArchiveId() + 1);
        }
        archive.setEmail(email);
        archive.setTask(task);
        return archiveRepo.save(archive);
    }

    @Override
    public List<Task> showArchiveList(String email) {
        List<Archive> archiveList = archiveRepo.findAll();
        List<Task> taskList = new ArrayList<>();
        for(Archive archive:archiveList){
            if(archive.getEmail().equalsIgnoreCase(email)){
                taskList.add(archive.getTask());
            }
        }
        return taskList;
    }

    @Override
    public String deleteTask(int taskId) throws UserNotFoundException {
        List<Archive> archiveList = archiveRepo.findAll();
        List<Task> taskList = new ArrayList<>();
        for(Archive archive:archiveList){
            if( archive.getTask().getTaskId()==taskId){
                archiveRepo.deleteById(archive.getArchiveId());
            }
        }
        return "task deleted successfully";
    }

    @Override
    public String unArchiveTask(int taskId) throws UserNotFoundException {

        List<Archive> archiveList = archiveRepo.findAll();
        List<Task> taskList = new ArrayList<>();
        for(Archive archive:archiveList){
            if( archive.getTask().getTaskId()==taskId){
                ResponseEntity r = archiveProxy.addTask1(archive.getTask(),archive.getEmail());
                archiveRepo.deleteById(archive.getArchiveId());
            }}
        return "Task Unarchived";
    }


}
