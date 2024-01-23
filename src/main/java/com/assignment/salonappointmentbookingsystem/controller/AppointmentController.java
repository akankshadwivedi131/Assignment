package com.assignment.salonappointmentbookingsystem.controller;


import com.assignment.salonappointmentbookingsystem.annotations.IsAdmin;
import com.assignment.salonappointmentbookingsystem.request.BookAppointmentRequest;
import com.assignment.salonappointmentbookingsystem.response.BookAppointmentResponse;
import com.assignment.salonappointmentbookingsystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    ResponseEntity<BookAppointmentResponse> book(@RequestBody BookAppointmentRequest bookAppointmentRequest) {
        if (Objects.isNull(bookAppointmentRequest.getCategoryId()) || Objects.isNull(bookAppointmentRequest.getUserId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category id or User id can not be blank");
        }
        return new ResponseEntity<>(appointmentService.book(bookAppointmentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/view")
    @IsAdmin
    ResponseEntity<List<BookAppointmentResponse>> viewAppointmentsByDate(@RequestParam("bookingDate") String bookingDate) {
        if (Objects.isNull(bookingDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking date can not be blank");
        }
        return new ResponseEntity<>(appointmentService.viewAppointmentsByDate(bookingDate), HttpStatus.OK);
    }
}
