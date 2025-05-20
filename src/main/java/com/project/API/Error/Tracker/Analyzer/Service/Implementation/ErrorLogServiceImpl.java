package com.project.API.Error.Tracker.Analyzer.Service.Implementation;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorLogRequest;
import com.project.API.Error.Tracker.Analyzer.Modal.ErrorLog;
import com.project.API.Error.Tracker.Analyzer.Repository.ErrorLogRepository;
import com.project.API.Error.Tracker.Analyzer.Service.Interface.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;






@Service
public class ErrorLogServiceImpl implements ErrorLogService{
    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Override
    public ErrorLog saveError(ErrorLogRequest request) {
        ErrorLog log = new ErrorLog();
        log.setServiceName(request.getServiceName());
        log.setEndpoint(request.getEndpoint());
        log.setStatusCode(request.getStatusCode());
        log.setMethod(request.getMethod());
        log.setErrorMessage(request.getErrorMessage());
        log.setStackTrace(request.getStackTrace());
        log.setTimestamp(Instant.now());

        return errorLogRepository.save(log);
    }

    @Override
    public List<ErrorLog> getFilteredErrors(String service, Integer statusCode) {
        if (service != null && statusCode != null)
            return errorLogRepository.findByServiceNameAndStatusCode(service, statusCode);
        if (service != null)
            return errorLogRepository.findByServiceName(service);
        if (statusCode != null)
            return errorLogRepository.findByStatusCode(statusCode);

        return errorLogRepository.findAll();
    }
}
