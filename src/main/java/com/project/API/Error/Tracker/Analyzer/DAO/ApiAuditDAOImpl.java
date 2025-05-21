package com.project.API.Error.Tracker.Analyzer.DAO;

import com.project.API.Error.Tracker.Analyzer.Entity.ApiAuditTable;
import com.project.API.Error.Tracker.Analyzer.Model.APIAuditModel;
import com.project.API.Error.Tracker.Analyzer.Repository.ApiAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApiAuditDAOImpl implements ApiAuditDAO {
    @Autowired
    private ApiAuditRepository apiAuditRepository;

    @Override
    public void save(APIAuditModel apiAuditModel) {
        ApiAuditTable entity = new ApiAuditTable();
        entity.setApiName(apiAuditModel.getApiName());
        entity.setApiUrl(apiAuditModel.getApiUrl());
        entity.setApiStatusCode(apiAuditModel.getApiStatusCode());
        entity.setResponseMessage(apiAuditModel.getResponseMessage());
        entity.setCreatedAt(apiAuditModel.getCreatedAt());
        ApiAuditTable saved = apiAuditRepository.save(entity);
    }
}
