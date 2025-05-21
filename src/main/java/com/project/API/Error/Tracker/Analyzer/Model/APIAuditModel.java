package com.project.API.Error.Tracker.Analyzer.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class APIAuditModel {
    private String apiName;
    private String apiUrl;
    private Integer apiStatusCode;
    private String responseMessage;
    private LocalDateTime createdAt;
}
