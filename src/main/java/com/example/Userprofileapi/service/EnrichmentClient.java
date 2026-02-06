package com.example.Userprofileapi.service;

import com.example.Userprofileapi.model.dto.EnrichmentData;
import com.example.Userprofileapi.model.dto.EnrichedUserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class EnrichmentClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${external.service.url}")
    private String baseUrl;

    @Retry(name = "backend")
    @CircuitBreaker(name = "backend", fallbackMethod = "fallback")
    public EnrichmentData fetchEnrichment(String userId) {
        return restTemplate.getForObject(baseUrl + "/" + userId, EnrichmentData.class);
    }

    public EnrichmentData fallback(String userId, Throwable t) {
        return null; // Triggers unavailable
    }
}
