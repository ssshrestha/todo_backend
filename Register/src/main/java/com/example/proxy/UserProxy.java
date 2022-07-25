package com.example.proxy;
//

import com.example.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="USER-AUTHENTICATION-SERVICE",url="localhost:8082")
public interface UserProxy {
    @PostMapping("/api/v1/register")
    public ResponseEntity registerUser(@RequestBody User user);
}
