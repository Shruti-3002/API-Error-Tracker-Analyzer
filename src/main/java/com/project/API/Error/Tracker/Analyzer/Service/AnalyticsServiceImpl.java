package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorStatsResponse;
import com.project.API.Error.Tracker.Analyzer.Repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AnalyticsServiceImpl implements AnalyticsService {
    @Autowired
    private ApiRepository apiRepository;

    @Override
    public ErrorStatsResponse getErrorStatistics() {
        long total = apiRepository.count();
        long status500 = apiRepository.countByStatusCode(500);
        long status404 = apiRepository.countByStatusCode(404);
        long status400 = apiRepository.countByStatusCode(400);

        return new ErrorStatsResponse(total, status500, status404, status400);
    }
}
