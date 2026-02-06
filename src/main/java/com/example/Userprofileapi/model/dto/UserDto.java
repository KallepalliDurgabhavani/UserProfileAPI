package com.example.Userprofileapi.model.dto;  // ← EXACTLY THIS

import java.time.LocalDateTime;

public record UserDto(
    String id, 
    String name, 
    String email, 
    LocalDateTime registrationDate
) {}
