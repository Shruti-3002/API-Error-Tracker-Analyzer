package com.project.API.Error.Tracker.Analyzer.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIRunRequestModel {
    String apiName;
    String apiUrl;
    String apiRequestBody;
    String apiRequestMethod;
}
