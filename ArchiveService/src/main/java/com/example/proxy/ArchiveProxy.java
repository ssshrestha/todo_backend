package com.example.proxy;
//

import com.example.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="TASK-SERVICE")
public interface ArchiveProxy {
    @PostMapping("/api/v3/addTaskArchive/{email}")
    public ResponseEntity addTask1(@RequestBody Task task, @PathVariable("email") String email);
}
