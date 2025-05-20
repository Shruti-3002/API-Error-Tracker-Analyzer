package com.project.API.Error.Tracker.Analyzer.Controller;

import com.project.API.Error.Tracker.Analyzer.Modal.ApiResponse;
import com.project.API.Error.Tracker.Analyzer.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @PostMapping("/logError")
    public void logError(@RequestBody ApiResponse request) {
        apiService.saveErrorLog(request);
    }

    @PostMapping("/runApi")
    public ResponseEntity<String> runApi(@RequestBody String request) {
        // Simulate running an API and returning a response
        return ResponseEntity.ok("API executed successfully");
    }
}
