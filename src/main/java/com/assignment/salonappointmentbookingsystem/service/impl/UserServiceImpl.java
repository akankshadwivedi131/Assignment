package com.assignment.salonappointmentbookingsystem.service.impl;

import com.assignment.salonappointmentbookingsystem.mapper.CommonMapper;
import com.assignment.salonappointmentbookingsystem.model.User;
import com.assignment.salonappointmentbookingsystem.repository.UserRepository;
import com.assignment.salonappointmentbookingsystem.request.UpdatePasswordRequest;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;
import com.assignment.salonappointmentbookingsystem.security.UserInformation;
import com.assignment.salonappointmentbookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResponse updatePassword(UpdatePasswordRequest updatePasswordRequest, UserInformation userInformation) {
        User user = userRepository.findByEmail(userInformation.getUser().getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not exists"));
        if (passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
            return commonMapper.userToUserResponse(userRepository.save(user));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Password");
    }
}
