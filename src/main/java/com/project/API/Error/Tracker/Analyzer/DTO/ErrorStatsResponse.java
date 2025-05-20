package com.project.API.Error.Tracker.Analyzer.DTO;

public class ErrorStatsResponse {
    private long totalErrors;
    private long status500Errors;
    private long status404Errors;
    private long status400Errors;

    public ErrorStatsResponse(long total, long s500, long s404, long s400) {
        this.totalErrors = total;
        this.status500Errors = s500;
        this.status404Errors = s404;
        this.status400Errors = s400;
    }
}
