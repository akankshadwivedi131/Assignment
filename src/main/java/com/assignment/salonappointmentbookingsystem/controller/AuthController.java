package com.assignment.salonappointmentbookingsystem.controller;

import com.assignment.salonappointmentbookingsystem.request.LoginRequest;
import com.assignment.salonappointmentbookingsystem.request.RegistrationRequest;
import com.assignment.salonappointmentbookingsystem.response.LoginResponse;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;
import com.assignment.salonappointmentbookingsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    ResponseEntity<UserResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        if (registrationRequest.getEmail().isBlank() || registrationRequest.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password can not be blank");
        }
        return new ResponseEntity<>(authService.register(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getEmail().isBlank() || loginRequest.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password can not be blank");
        }
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}
