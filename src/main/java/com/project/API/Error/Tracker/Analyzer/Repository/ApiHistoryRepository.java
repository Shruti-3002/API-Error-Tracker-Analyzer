package com.project.API.Error.Tracker.Analyzer.Repository;

import com.project.API.Error.Tracker.Analyzer.Entity.ApiAuditTable;
import com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApiHistoryRepository extends JpaRepository<ApiAuditTable, Long> {
    @Query("SELECT new com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel(" +
           "a.apiName, a.apiUrl, a.apiStatusCode, " +
           "a.responseMessage, a.createdAt) " +
           "FROM ApiAuditTable a WHERE a.apiName = :apiName " +
           "ORDER BY a.createdAt DESC")
    List<APIHistoryModel> findTop5ByApiNameOrderByTimestampDesc(@Param("apiName") String apiName);

    @Query("SELECT new com.project.API.Error.Tracker.Analyzer.Model.APIHistoryModel(" +
           "a.apiName, a.apiStatusCode, a.createdAt) " +
           "FROM ApiAuditTable a " +
           "WHERE (a.apiName, a.createdAt) IN " +
           "(SELECT b.apiName, MAX(b.createdAt) " +
           "FROM ApiAuditTable b GROUP BY b.apiName)")
    List<APIHistoryModel> findAllAPIData();
}
