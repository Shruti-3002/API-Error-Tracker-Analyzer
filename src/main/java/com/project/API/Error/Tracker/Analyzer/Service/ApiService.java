package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.Model.APIAuditModel;

public interface ApiService {
    APIAuditModel runApi(String apiURL, String method, String requestBody);
    void saveErrorLog();
}
