package com.assignment.salonappointmentbookingsystem.controller;

import com.assignment.salonappointmentbookingsystem.annotations.CurrentUser;
import com.assignment.salonappointmentbookingsystem.request.LoginRequest;
import com.assignment.salonappointmentbookingsystem.request.UpdatePasswordRequest;
import com.assignment.salonappointmentbookingsystem.response.LoginResponse;
import com.assignment.salonappointmentbookingsystem.response.UserResponse;
import com.assignment.salonappointmentbookingsystem.security.UserInformation;
import com.assignment.salonappointmentbookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/update-password")
    ResponseEntity<UserResponse> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest, @CurrentUser UserInformation userInformation) {
        if (updatePasswordRequest.getOldPassword().isBlank() || updatePasswordRequest.getNewPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or old password or new password can not be blank");
        }
        return new ResponseEntity<>(userService.updatePassword(updatePasswordRequest, userInformation), HttpStatus.OK);
    }
}
