package com.example.repo;

import com.example.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepo  extends MongoRepository<User,Integer> {
    public User findByEmail(String email);
}
