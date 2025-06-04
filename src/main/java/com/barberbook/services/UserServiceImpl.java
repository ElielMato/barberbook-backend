package com.barberbook.services;

import com.barberbook.DTOs.UserRegistrationRequest;
import com.barberbook.DTOs.UserResponse;
import com.barberbook.common.exceptions.EmailAlreadyExistsException;
import com.barberbook.models.User;
import com.barberbook.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse registerUser(UserRegistrationRequest registrationRequest) {

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Error: Email '" + registrationRequest.getEmail() + "' is already taken!");
        }

        User user = User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phone(registrationRequest.getPhone())
                .role(registrationRequest.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}