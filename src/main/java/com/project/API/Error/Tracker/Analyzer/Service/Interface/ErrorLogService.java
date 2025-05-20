package com.project.API.Error.Tracker.Analyzer.Service.Interface;
import com.project.API.Error.Tracker.Analyzer.DTO.ErrorLogRequest;
import com.project.API.Error.Tracker.Analyzer.Modal.ErrorLog;

import java.util.List;

public interface ErrorLogService {

    ErrorLog saveError(ErrorLogRequest request);
    List<ErrorLog> getFilteredErrors(String service, Integer statusCode);
}
