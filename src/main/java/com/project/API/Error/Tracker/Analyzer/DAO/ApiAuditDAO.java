package com.project.API.Error.Tracker.Analyzer.DAO;

import com.project.API.Error.Tracker.Analyzer.Model.APIAuditModel;

public interface ApiAuditDAO {
    void save(APIAuditModel apiAuditModel);
}
