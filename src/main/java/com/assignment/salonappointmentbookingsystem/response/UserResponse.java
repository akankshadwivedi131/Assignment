package com.assignment.salonappointmentbookingsystem.response;

import com.assignment.salonappointmentbookingsystem.model.Role;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private Role role;
}
