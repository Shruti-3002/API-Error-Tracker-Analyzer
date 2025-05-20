package com.project.API.Error.Tracker.Analyzer.Controller;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorLogRequest;
import com.project.API.Error.Tracker.Analyzer.Modal.ErrorLog;
import com.project.API.Error.Tracker.Analyzer.Service.Interface.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ErrorLogController {
    @Autowired
    private ErrorLogService errorLogService;

    @PostMapping
    public ResponseEntity<ErrorLog> logError(@RequestBody ErrorLogRequest request) {
        return ResponseEntity.ok(errorLogService.saveError(request));
    }

    @GetMapping
    public ResponseEntity<List<ErrorLog>> getAllErrors(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) Integer statusCode) {
        return ResponseEntity.ok(errorLogService.getFilteredErrors(service, statusCode));
    }
}
