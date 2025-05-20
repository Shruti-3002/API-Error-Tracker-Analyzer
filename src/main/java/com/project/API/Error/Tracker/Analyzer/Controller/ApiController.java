package com.project.API.Error.Tracker.Analyzer.Controller;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorLogRequest;
import com.project.API.Error.Tracker.Analyzer.Modal.ErrorLog;
import com.project.API.Error.Tracker.Analyzer.Service.Interface.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ErrorLogService errorLogService;

    @PostMapping("/logError")
    public ResponseEntity<ErrorLog> logError(@RequestBody ErrorLogRequest request) {
        return ResponseEntity.ok(errorLogService.saveError(request));
    }

    @PostMapping("/runApi")
    public ResponseEntity<String> runApi(@RequestBody String request) {
        // Simulate running an API and returning a response
        return ResponseEntity.ok("API executed successfully");
    }
}
