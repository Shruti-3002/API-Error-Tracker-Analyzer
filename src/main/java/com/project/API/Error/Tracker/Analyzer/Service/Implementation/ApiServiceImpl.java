package com.project.API.Error.Tracker.Analyzer.Service.Implementation;

import com.project.API.Error.Tracker.Analyzer.Modal.ApiResponse;
import com.project.API.Error.Tracker.Analyzer.Service.Interface.ApiService;
import com.project.API.Error.Tracker.Analyzer.Service.Interface.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ErrorLogService errorLogService;

    @Override
    public ApiResponse runApi(String apiURL, String method, String requestBody) {
        try {
            HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                apiURL, httpMethod, entity, String.class);

            return new ApiResponse(
                String.valueOf(response.getStatusCodeValue()),
                response.getBody()
            );
        } catch (Exception ex) {
            // This error is from the application side, hence we do not log it, handle it such that log error button should be disabled
            return new ApiResponse(
                "500",
                "Error occurred while executing API: " + ex.getMessage()
            );
        }
    }
}