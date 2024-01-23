package com.assignment.salonappointmentbookingsystem.service;

import com.assignment.salonappointmentbookingsystem.request.LoginRequest;
import com.assignment.salonappointmentbookingsystem.request.RegistrationRequest;
import com.assignment.salonappointmentbookingsystem.response.LoginResponse;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;

public interface AuthService {
    UserResponse register(RegistrationRequest registrationRequest);

    LoginResponse login(LoginRequest loginRequest);
}
