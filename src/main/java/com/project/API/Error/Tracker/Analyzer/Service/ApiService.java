package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.Modal.ApiResponse;

public interface ApiService {
    ApiResponse runApi(String apiURL, String method, String requestBody);
    void saveErrorLog(ApiResponse apiResponse);

}
