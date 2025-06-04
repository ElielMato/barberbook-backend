package com.barberbook.services;

import com.barberbook.DTOs.UserRegistrationRequest;
import com.barberbook.DTOs.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest registrationRequest);
}