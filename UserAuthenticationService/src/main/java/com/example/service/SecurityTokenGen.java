package com.example.service;

import com.example.entity.User;
import java.util.Map;

public interface SecurityTokenGen {
    Map<String,String> generateToken(User user);
}
