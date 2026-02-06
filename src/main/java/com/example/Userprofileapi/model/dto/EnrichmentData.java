package com.example.Userprofileapi.model.dto;

import java.util.List;

public record EnrichmentData(
    List<String> recentActivity,
    int loyaltyScore
) {}
