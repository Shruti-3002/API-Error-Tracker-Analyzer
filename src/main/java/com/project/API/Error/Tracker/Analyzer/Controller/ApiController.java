package com.project.API.Error.Tracker.Analyzer.Controller;

import com.project.API.Error.Tracker.Analyzer.Model.APIAuditModel;
import com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel;
import com.project.API.Error.Tracker.Analyzer.Model.APIRunRequestModel;
import com.project.API.Error.Tracker.Analyzer.Service.AnalyticsService;
import com.project.API.Error.Tracker.Analyzer.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiService;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/logError")
    public ResponseEntity<String> logError() {
        apiService.saveErrorLog();
        return ResponseEntity.ok("Error logged successfully");
    }

    @PostMapping("/runApi")
    public ResponseEntity<String> runApi(@RequestBody APIRunRequestModel request) {
        // Simulate running an API and returning a response
        APIAuditModel apiAuditModel = apiService.runApi(request.getApiName(), request.getApiUrl(), request.getApiRequestMethod(), request.getApiRequestBody());
        return ResponseEntity.ok("API executed successfully. Status Code: " + apiAuditModel.getApiStatusCode() + ", Response: " + apiAuditModel.getResponseMessage());
    }

    @GetMapping("/getAPIHistory")
    public ResponseEntity<List<APIHistoryModel>> getAPIHistory(@RequestParam String apiName) {
        List<APIHistoryModel> history = analyticsService.getAPIHistory(apiName);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/getAllAPIData")
    public ResponseEntity<List<APIHistoryModel>> getAllAPIData() {
        List<APIHistoryModel> allData = analyticsService.getAllAPIData();
        return ResponseEntity.ok(allData);
    }
}
