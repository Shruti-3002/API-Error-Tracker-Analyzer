package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.DTO.ErrorStatsResponse;

public interface AnalyticsService {
    ErrorStatsResponse getErrorStatistics();
}
