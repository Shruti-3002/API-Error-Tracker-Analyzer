package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.DAO.ApiHistoryDAO;
import com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private ApiHistoryDAO apiHistoryDAO;

    @Override
    public List<APIHistoryModel> getAPIHistory(String apiName) {
        return apiHistoryDAO.findTop5ByApiName(apiName);
    }

    @Override
    public List<APIHistoryModel> getAllAPIData() {
        return apiHistoryDAO.findAll();
    }
}
