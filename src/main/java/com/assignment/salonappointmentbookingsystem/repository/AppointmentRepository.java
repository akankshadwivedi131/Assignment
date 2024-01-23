package com.assignment.salonappointmentbookingsystem.repository;

import com.assignment.salonappointmentbookingsystem.model.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {
    Boolean existsByUserIdAndCategoryId(ObjectId userId, ObjectId categoryId);

    List<Appointment> findByBookingDate(LocalDate bookingDate);
}
