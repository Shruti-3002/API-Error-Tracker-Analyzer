package com.project.API.Error.Tracker.Analyzer.DAO;

import com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel;
import com.project.API.Error.Tracker.Analyzer.Repository.ApiHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ApiHistoryDAOImpl implements ApiHistoryDAO {

    @Autowired
    private ApiHistoryRepository apiHistoryRepository;

    @Override
    public List<APIHistoryModel> findTop5ByApiName(String apiName) {
        return apiHistoryRepository.findTop5ByApiNameOrderByTimestampDesc(apiName);
    }

    @Override
    public List<APIHistoryModel> findAll() {
        return apiHistoryRepository.findAllAPIData();
    }
}
