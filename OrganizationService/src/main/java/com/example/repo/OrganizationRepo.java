package com.example.repo;

import com.example.model.Task;
import com.example.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepo extends MongoRepository<User,Integer> {
    public User findByEmail(String email);
    public Task findById(int taskId);
}
