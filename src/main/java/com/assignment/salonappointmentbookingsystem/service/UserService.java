package com.assignment.salonappointmentbookingsystem.service;

import com.assignment.salonappointmentbookingsystem.model.User;
import com.assignment.salonappointmentbookingsystem.request.UpdatePasswordRequest;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;
import com.assignment.salonappointmentbookingsystem.security.UserInformation;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmail(String email);

    UserResponse updatePassword(UpdatePasswordRequest updatePasswordRequest, UserInformation userInformation);
}
