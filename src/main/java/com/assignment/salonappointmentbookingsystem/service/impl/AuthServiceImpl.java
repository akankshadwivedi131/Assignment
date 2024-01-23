package com.assignment.salonappointmentbookingsystem.service.impl;

import com.assignment.salonappointmentbookingsystem.mapper.CommonMapper;
import com.assignment.salonappointmentbookingsystem.model.User;
import com.assignment.salonappointmentbookingsystem.repository.UserRepository;
import com.assignment.salonappointmentbookingsystem.request.LoginRequest;
import com.assignment.salonappointmentbookingsystem.request.RegistrationRequest;
import com.assignment.salonappointmentbookingsystem.response.LoginResponse;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;
import com.assignment.salonappointmentbookingsystem.security.JwtTokenProvider;
import com.assignment.salonappointmentbookingsystem.service.AuthService;
import com.assignment.salonappointmentbookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CommonMapper commonMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public UserResponse register(RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered please try another one.");
        }
        return commonMapper.userToUserResponse(userRepository.save(commonMapper.registrationRequestToUser(registrationRequest)));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail().trim(), loginRequest.getPassword().trim()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            String token = jwtTokenProvider.generateToken(authenticate);
            return LoginResponse.builder().type("Bearer").token(token).build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }
    }
}
