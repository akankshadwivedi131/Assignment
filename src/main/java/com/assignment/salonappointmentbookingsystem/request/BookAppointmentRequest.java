package com.assignment.salonappointmentbookingsystem.request;

import lombok.Data;

import java.time.LocalDate;


@Data
public class BookAppointmentRequest {
    private String userId;
    private String categoryId;
    private LocalDate bookingDate = LocalDate.now();
}
