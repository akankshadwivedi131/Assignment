package com.assignment.salonappointmentbookingsystem.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookAppointmentResponse {
    private String id;
    private String userId;
    private String categoryId;
    private LocalDate bookingDate;
}
