package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel;
import java.util.List;

public interface AnalyticsService {
    List<APIHistoryModel> getAPIHistory(String apiName);
    List<APIHistoryModel> getAllAPIData();
}
