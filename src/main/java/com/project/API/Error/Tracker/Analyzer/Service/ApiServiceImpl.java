package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.DAO.ApiAuditDAO;
import com.project.API.Error.Tracker.Analyzer.Model.APIAuditModel;
import com.project.API.Error.Tracker.Analyzer.Config.CustomCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.CacheManager;

import java.util.Objects;
import java.util.UUID;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiAuditDAO apiAuditDAO;

    @Autowired
    private CacheManager cacheManager;

    private static final String CACHE_NAME = CustomCacheConfig.API_RESPONSE_CACHE;

    @Override
    public APIAuditModel runApi(String apiURL, String method, String requestBody) {
        APIAuditModel auditModel = new APIAuditModel();
        auditModel.setApiName("DummyName");
        auditModel.setApiUrl(apiURL);
        auditModel.setCreatedAt(java.time.LocalDateTime.now());

        try {
            HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                apiURL, httpMethod, entity, String.class);

            auditModel.setApiStatusCode(response.getStatusCode().value());
            auditModel.setResponseMessage(response.getBody());

        } catch (Exception ex) {
            auditModel.setApiStatusCode(500);
            auditModel.setResponseMessage("Error occurred while executing API: " + ex.getMessage());
        }

        // Store in cache with a unique key
        String cacheKey = UUID.randomUUID().toString();
        Objects.requireNonNull(cacheManager.getCache(CACHE_NAME)).put(cacheKey, auditModel);

        return auditModel;
    }

    @Override
    public void saveErrorLog() {
        // Remove the latest (top) element from the FILO cache and save to DB
        CustomCacheConfig.FiloCache cache = (CustomCacheConfig.FiloCache) cacheManager.getCache(CACHE_NAME);
        assert cache != null;
        APIAuditModel model = (APIAuditModel) cache.removeLatest();
        if (model != null) {
            apiAuditDAO.save(model);
        }
    }
}
