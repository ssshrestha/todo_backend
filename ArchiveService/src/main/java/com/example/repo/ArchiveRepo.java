package com.example.repo;

import com.example.model.Archive;
import com.example.model.Task;
import com.example.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArchiveRepo extends MongoRepository<Archive,Integer> {
    public Archive findByEmail(String email);
}
