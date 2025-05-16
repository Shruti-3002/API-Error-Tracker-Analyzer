package com.project.API.Error.Tracker.Analyzer.Service.Interface;
import com.project.API.Error.Tracker.Analyzer.DTO.ErrorLogRequest;
import com.project.API.Error.Tracker.Analyzer.Entity.ErrorLog;

public interface ErrorLogService {

    ErrorLog saveError(ErrorLogRequest request);
    List<ErrorLog> getFilteredErrors(String service, Integer statusCode);
}
