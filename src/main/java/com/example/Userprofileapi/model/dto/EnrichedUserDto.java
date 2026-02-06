package com.example.Userprofileapi.model.dto;

public record EnrichedUserDto(
    UserDto user,
    EnrichmentData enrichedData,
    String enrichedDataStatus  // "available" or "unavailable"
) {}
