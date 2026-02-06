package com.example.Userprofileapi.service;

import com.example.Userprofileapi.model.User;
import com.example.Userprofileapi.model.dto.CreateUserDto;
import com.example.Userprofileapi.model.dto.UserDto;
import com.example.Userprofileapi.repository.IUserRepository;  // ← Fix this

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnitOfWork {
    private final IUserRepository userRepository;

    @Transactional
    public UserDto create(CreateUserDto dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email exists");
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(dto.name());
        user.setEmail(dto.email());
        User saved = userRepository.save(user);
        return new UserDto(saved.getId(), saved.getName(), saved.getEmail(), saved.getRegistrationDate());
    }

    @Transactional(readOnly = true)
    public UserDto findById(String id) {
        return userRepository.findById(id)
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail(), u.getRegistrationDate()))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public UserDto update(String id, CreateUserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getEmail().equals(dto.email()) && userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email duplicate");
        }
        user.setName(dto.name());
        user.setEmail(dto.email());
        User saved = userRepository.save(user);
        return new UserDto(saved.getId(), saved.getName(), saved.getEmail(), saved.getRegistrationDate());
    }

    @Transactional
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
