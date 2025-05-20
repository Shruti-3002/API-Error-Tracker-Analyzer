package com.project.API.Error.Tracker.Analyzer.Repository;

import com.project.API.Error.Tracker.Analyzer.Entity.ApiEntity;
import com.project.API.Error.Tracker.Analyzer.Modal.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ApiRepository extends JpaRepository<ApiEntity, UUID> {

    @Query("INSERT INTO ApiEntity (apiURL, method, requestBody) VALUES (:apiURL, :method, :requestBody)")
    public void save(ApiResponse apiResponse);
}
