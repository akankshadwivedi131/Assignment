package com.assignment.salonappointmentbookingsystem.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
public class LoginResponse {
    private String type;
    private String token;
}
