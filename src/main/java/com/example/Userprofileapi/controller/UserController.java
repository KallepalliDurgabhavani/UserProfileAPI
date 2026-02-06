package com.example.Userprofileapi.controller;

import com.example.Userprofileapi.model.dto.*;
import com.example.Userprofileapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto dto) {
        UserDto created = userService.create(dto);
        return ResponseEntity
                .created(URI.create("/api/users/" + created.id()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("User not found: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable String id, 
            @Valid @RequestBody CreateUserDto dto) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, dto));
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                throw new NoSuchElementException("User not found: " + id);
            }
            throw new IllegalArgumentException("Email duplicate or invalid");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("User not found: " + id);
        }
    }

    @GetMapping("/{id}/enriched")
    public ResponseEntity<EnrichedUserDto> getEnrichedUser(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getEnriched(id));
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("User not found: " + id);
        }
    }
}
