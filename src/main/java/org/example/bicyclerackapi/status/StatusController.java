package org.example.bicyclerackapi.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/status")
public class StatusController {
    @GetMapping
    public StatusResponse getStatus() {
        return new StatusResponse(Instant.now(), 200, "Server is up and running");
    }
}
