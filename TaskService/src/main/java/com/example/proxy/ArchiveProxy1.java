package com.example.proxy;
//

import com.example.model.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="ARCHIVE-SERVICE")
public interface ArchiveProxy1 {
    @PostMapping("/api/v4/addTask/{email}")
    public ResponseEntity addTask(@RequestBody Task task, @PathVariable("email") String email);
}
