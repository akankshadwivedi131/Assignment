package com.assignment.salonappointmentbookingsystem.request;

import com.assignment.salonappointmentbookingsystem.model.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
    private String fullName;
    private Role role = Role.ROLE_USER;
}
