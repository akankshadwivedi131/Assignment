package com.assignment.salonappointmentbookingsystem.service.impl;

import com.assignment.salonappointmentbookingsystem.mapper.CommonMapper;
import com.assignment.salonappointmentbookingsystem.repository.AppointmentRepository;
import com.assignment.salonappointmentbookingsystem.request.BookAppointmentRequest;
import com.assignment.salonappointmentbookingsystem.response.BookAppointmentResponse;
import com.assignment.salonappointmentbookingsystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CommonMapper commonMapper;

    @Override
    public BookAppointmentResponse book(BookAppointmentRequest bookAppointmentRequest) {
        if (appointmentRepository.existsByUserIdAndCategoryId(new ObjectId(bookAppointmentRequest.getUserId()), new ObjectId(bookAppointmentRequest.getCategoryId()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Appointment Exists please try another one.");
        }
        return commonMapper.appointmentToAppointmentResponse(appointmentRepository.save(commonMapper.appointmentRequestToAppointment(bookAppointmentRequest)));
    }

    @Override
    public List<BookAppointmentResponse> viewAppointmentsByDate(String bookingTime) {
        return appointmentRepository.findByBookingDate(LocalDate.parse(bookingTime)).stream().map(commonMapper::appointmentToAppointmentResponse).collect(Collectors.toList());
    }
}
