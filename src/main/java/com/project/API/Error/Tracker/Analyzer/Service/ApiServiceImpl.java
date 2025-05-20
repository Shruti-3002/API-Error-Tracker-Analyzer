package com.project.API.Error.Tracker.Analyzer.Service;

import com.project.API.Error.Tracker.Analyzer.Modal.ApiResponse;
import com.project.API.Error.Tracker.Analyzer.Repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import com.project.API.Error.Tracker.Analyzer.Config.CustomCacheConfig;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    ApiRepository apiRepository;

    @Override
    public ApiResponse runApi(String apiURL, String method, String requestBody) {
        try {
            HttpMethod httpMethod = HttpMethod.valueOf(method.toUpperCase());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                apiURL, httpMethod, entity, String.class);

            ApiResponse apiResponse = new ApiResponse(
                String.valueOf(response.getStatusCodeValue()),
                response.getBody()
            );
            // Store in FILO cache
            Cache cache = cacheManager.getCache(CustomCacheConfig.API_RESPONSE_CACHE);
            if (cache != null) {
                cache.put(System.currentTimeMillis(), apiResponse);
            }
            return apiResponse;
        } catch (Exception ex) {
            // This error is from the application side, hence we do not log it, handle it such that log error button should be disabled
            ApiResponse errorResponse = new ApiResponse(
                "500",
                "Error occurred while executing API: " + ex.getMessage()
            );
            // Store error in cache as well
            Cache cache = cacheManager.getCache(CustomCacheConfig.API_RESPONSE_CACHE);
            if (cache != null) {
                cache.put(System.currentTimeMillis(), errorResponse);
            }
            return errorResponse;
        }
    }

    @Override
    public void saveErrorLog(ApiResponse apiResponse) {
        // Retrieve and remove the latest entry from the FILO cache
        Cache cache = cacheManager.getCache(CustomCacheConfig.API_RESPONSE_CACHE);
        if (cache != null && cache.getNativeCache() instanceof java.util.concurrent.ConcurrentHashMap) {
            if (cache instanceof CustomCacheConfig.FiloCache filoCache) {
                Object latest = filoCache.removeLatest();
                if (latest instanceof ApiResponse responseToSave) {
                    apiRepository.save(responseToSave);
                }
            }
        }
    }
}

