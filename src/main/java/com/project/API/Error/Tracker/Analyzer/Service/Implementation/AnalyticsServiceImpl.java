package com.project.API.Error.Tracker.Analyzer.Service.Implementation;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorStatsResponse;
import com.project.API.Error.Tracker.Analyzer.Repository.ErrorLogRepository;
import com.project.API.Error.Tracker.Analyzer.Service.Interface.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Override
    public ErrorStatsResponse getErrorStatistics() {
        long total = errorLogRepository.count();
        long status500 = errorLogRepository.countByStatusCode(500);
        long status404 = errorLogRepository.countByStatusCode(404);
        long status400 = errorLogRepository.countByStatusCode(400);

        return new ErrorStatsResponse(total, status500, status404, status400);
    }
}
