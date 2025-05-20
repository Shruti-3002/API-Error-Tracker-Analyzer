package com.project.API.Error.Tracker.Analyzer.Modal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String statusCode;
    private Object responseMessage;
}
