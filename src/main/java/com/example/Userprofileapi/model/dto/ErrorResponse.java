package com.example.Userprofileapi.model.dto;

import java.util.List;

public record ErrorResponse(
    String errorCode,
    String message,
    List<String> details
) {}
