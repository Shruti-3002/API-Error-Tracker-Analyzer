package com.project.API.Error.Tracker.Analyzer.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIHistoryModel {
    private String apiName;
    private String apiUrl;
    private Integer apiStatusCode;
    private String responseMessage;
    private LocalDateTime createdAt;

    public APIHistoryModel(String apiName) {
        this.apiName = apiName;
    }

    public APIHistoryModel(String apiName, LocalDateTime createdAt) {
        this.apiName = apiName;
        this.createdAt = createdAt;
    }

    public APIHistoryModel(String apiName, Integer apiStatusCode, LocalDateTime createdAt) {
        this.apiName = apiName;
        this.apiStatusCode = apiStatusCode;
        this.createdAt = createdAt;
    }
}
