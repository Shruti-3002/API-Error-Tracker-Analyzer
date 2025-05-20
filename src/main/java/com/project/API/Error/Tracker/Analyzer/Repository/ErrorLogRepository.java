package com.project.API.Error.Tracker.Analyzer.Repository;

import com.project.API.Error.Tracker.Analyzer.Modal.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, UUID> {

    List<ErrorLog> findByServiceName(String serviceName);
    List<ErrorLog> findByStatusCode(Integer statusCode);
    List<ErrorLog> findByServiceNameAndStatusCode(String serviceName, Integer statusCode);

    long countByStatusCode(Integer statusCode);
}
