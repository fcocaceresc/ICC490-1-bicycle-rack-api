package com.example.bicyclerackapi.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class StatusController {
    @GetMapping("/status")
    public Record getStatus() {
        return new StatusResponse("Server is up and running", LocalDateTime.now());
    }
}
