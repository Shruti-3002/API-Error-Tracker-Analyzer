package com.project.API.Error.Tracker.Analyzer.Repository;

import com.project.API.Error.Tracker.Analyzer.Entity.ApiAuditTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiAuditRepository extends JpaRepository<ApiAuditTable, Long> {
    // No custom methods needed for basic save operation
}

