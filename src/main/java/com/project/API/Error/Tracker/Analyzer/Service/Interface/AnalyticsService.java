package com.project.API.Error.Tracker.Analyzer.Service.Interface;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorStatsResponse;

public interface AnalyticsService {
    ErrorStatsResponse getErrorStatistics();
}
