package com.assignment.salonappointmentbookingsystem.service;

import com.assignment.salonappointmentbookingsystem.request.BookAppointmentRequest;
import com.assignment.salonappointmentbookingsystem.response.BookAppointmentResponse;

import java.util.List;

public interface AppointmentService {

    BookAppointmentResponse book(BookAppointmentRequest bookAppointmentRequest);

    List<BookAppointmentResponse> viewAppointmentsByDate(String bookingTime);
}
