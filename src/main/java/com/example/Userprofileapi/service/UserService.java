package com.example.Userprofileapi.service;

import com.example.Userprofileapi.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UnitOfWork unitOfWork;
    private final EnrichmentClient enrichmentClient;

    public UserDto create(CreateUserDto dto) {
        return unitOfWork.create(dto);
    }

    public UserDto getUser(String id) {
        return unitOfWork.findById(id);
    }

    public UserDto updateUser(String id, CreateUserDto dto) {
        return unitOfWork.update(id, dto);
    }

    public void deleteUser(String id) {
        unitOfWork.delete(id);
    }

    public EnrichedUserDto getEnriched(String id) {
        UserDto user = unitOfWork.findById(id);
        EnrichmentData data = enrichmentClient.fetchEnrichment(id);
        String status = data != null ? "available" : "unavailable";
        EnrichmentData fallback = data != null ? data : new EnrichmentData(java.util.List.of(), 0);
        return new EnrichedUserDto(user, fallback, status);
    }
}
