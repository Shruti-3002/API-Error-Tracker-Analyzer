package com.project.API.Error.Tracker.Analyzer.Controller;

import com.project.API.Error.Tracker.Analyzer.Model.APIAuditModel;
import com.project.API.Error.Tracker.Analyzer.Model.APIRunRequestModel;
import com.project.API.Error.Tracker.Analyzer.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @GetMapping("/logError")
    public ResponseEntity<String> logError() {
        apiService.saveErrorLog();
        return ResponseEntity.ok("Error logged successfully");
    }

    @PostMapping("/runApi")
    public ResponseEntity<String> runApi(@RequestBody APIRunRequestModel request) {
        // Simulate running an API and returning a response
        APIAuditModel apiAuditModel = apiService.runApi(request.getApiUrl(), request.getApiRequestMethod(), request.getApiRequestBody());
        return ResponseEntity.ok("API executed successfully. Status Code: " + apiAuditModel.getApiStatusCode() + ", Response: " + apiAuditModel.getResponseMessage());
    }
}
