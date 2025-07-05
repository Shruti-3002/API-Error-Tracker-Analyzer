package com.project.API.Error.Tracker.Analyzer.DAO;

import com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel;
import java.util.List;

public interface ApiHistoryDAO {
    List<APIHistoryModel> findTop5ByApiName(String apiName);
    List<APIHistoryModel> findAll();
}
